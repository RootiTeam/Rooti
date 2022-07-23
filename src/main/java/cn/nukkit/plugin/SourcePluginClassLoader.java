package cn.nukkit.plugin;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SourcePluginClassLoader extends URLClassLoader {
   private String pluginPath = "";
   private SourcePluginLoader loader = null;
   private Map<String, Class> classes = new HashMap();

   public SourcePluginClassLoader(SourcePluginLoader loader, ClassLoader parent, File file, String pluginPath) throws MalformedURLException {
      super(new URL[]{file.toURI().toURL()}, parent);
      this.loader = loader;
      this.pluginPath = pluginPath.replace("\\", "/") + "/";
   }

   protected Class findClass(String name) throws ClassNotFoundException {
      return this.findClass(name, true);
   }

   protected Class findClass(String name, boolean checkGlobal) throws ClassNotFoundException {
      if (!name.startsWith("cn.nukkit.") && !name.startsWith("net.minecraft.")) {
         Class result = (Class)this.classes.get(name);
         if (result == null) {
            if (checkGlobal) {
               result = this.loader.getClassByName(name);
            }

            if (result == null) {
               result = super.findClass(name);
               if (result != null) {
                  this.loader.setClass(name, result);
               }
            }

            this.classes.put(name, result);
         }

         return result;
      } else {
         throw new ClassNotFoundException(name);
      }
   }

   public URL findResource(String name) {
      try {
         File res = new File(this.pluginPath + "resources/" + name);
         if (res.exists()) {
            return res.toURI().toURL();
         }
      } catch (Exception var3) {
      }

      return null;
   }

   public Set getClasses() {
      return this.classes.keySet();
   }
}

