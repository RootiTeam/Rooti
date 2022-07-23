package cn.nukkit.plugin;

import cn.nukkit.Server;
import cn.nukkit.event.plugin.PluginDisableEvent;
import cn.nukkit.event.plugin.PluginEnableEvent;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.utils.Utils;
import java.io.File;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.JavaCompiler.CompilationTask;

public class SourcePluginLoader implements PluginLoader {
   private Server server = null;
   private JavaCompiler compiler = null;
   private StandardJavaFileManager filemanager = null;
   private Map<String, Class> classes = new HashMap();
   private Map<PluginBase, File> pluginPath = new HashMap();
   private Map<String, SourcePluginClassLoader> classLoaders = new HashMap();

   public SourcePluginLoader(Server server) {
      this.server = server;
      this.compiler = server.getCompiler();
      this.filemanager = this.compiler.getStandardFileManager((DiagnosticListener)null, (Locale)null, (Charset)null);
   }


   public static List<File> listFolder(File input, String filter) {
      List<File> result = new ArrayList();
      if (input != null) {
         if (!input.isDirectory()) {
            if (filter.equals("") || input.toString().endsWith("." + filter)) {
               result.add(input);
            }

            return result;
         }

         File[] var3 = input.listFiles();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            File f = var3[var5];
            result.addAll(listFolder(f, filter));
         }
      }

      return result;
   }

   public static void removeFolder(File input, String filter) {
      if (input != null) {
         if (input.isFile()) {
            if (filter.equals("") || input.toString().endsWith("." + filter)) {
               input.delete();
            }
         } else if (input.isDirectory()) {
            File[] var2 = input.listFiles();
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               File f = var2[var4];
               removeFolder(f, filter);
            }

            input.delete();
         }
      }

   }
   
   public Plugin loadPlugin(File dir) throws Exception {
      PluginDescription description = this.getPluginDescription(dir);
      if (description != null) {
         if (this.server.getPluginManager().getPlugin(description.getName()) != null) {
            this.server.getLogger().warning("Can't load source plugin \"" + description.getName() + "\": plugin exists");
            return null;
         } else {
            this.server.getLogger().info(TextFormat.AQUA + "Loading source plugin \"" + description.getName() + "\"");
            File class_file = new File(dir.getAbsolutePath() + "/src_compile");
            this.compilePlugin(dir, class_file);
            SourcePluginClassLoader classLoader = new SourcePluginClassLoader(this, this.getClass().getClassLoader(), class_file, dir.getAbsolutePath());
            this.classLoaders.put(description.getName(), classLoader);
            File dataFolder = new File(dir.getParentFile(), description.getName());
            if (dataFolder.exists() && !dataFolder.isDirectory()) {
               throw new IllegalStateException("Compile target folder \"" + dataFolder.toString() + "\" for " + description.getName() + " exists and is not a directory");
            } else {
               try {
                  PluginBase plugin = (PluginBase)classLoader.loadClass(description.getMain()).asSubclass(PluginBase.class).newInstance();
                  this.initPlugin(plugin, description, dataFolder, dir);
                  this.pluginPath.put(plugin, dir);
                  return plugin;
               } catch (ClassCastException var7) {
                  throw new Exception("Main class \"" + description.getMain() + "\" does not extend PluginBase");
               } catch (ClassNotFoundException var8) {
                  throw new Exception("Couldn't load plugin " + description.getName() + ": main class not found");
               }
            }
         }
      } else {
         return null;
      }
   }

   public PluginDescription getPluginDescription(File dir) {
      try {
         File yml = new File(dir.getAbsolutePath(), "plugin.yml");
         return dir.isDirectory() && yml.isFile() ? new PluginDescription(Utils.readFile(yml)) : null;
      } catch (Exception var3) {
         var3.printStackTrace();
         return null;
      }
   }

   public Pattern[] getPluginFilters() {
      return new Pattern[]{Pattern.compile(".+")};
   }

   public boolean compilePlugin(File dir, File output) throws Exception {
      if (output.isFile()) {
         throw new Exception("Compile output path exists and isn't a directory");
      } else {
         this.removeFolder(output, "class");
         if (!output.mkdirs()) {
            throw new Exception("Couldn't create compile output directory");
         } else {
            List files = this.listFolder(new File(dir.getAbsolutePath() + "/src"), "java");
            CompilationTask task = this.compiler.getTask((Writer)null, this.filemanager, (DiagnosticListener)null, Arrays.asList("-d", output.getAbsolutePath(), "-encoding", "utf-8"), (Iterable)null, this.filemanager.getJavaFileObjects((File[])((File[])files.toArray(new File[files.size()]))));
            return task.call();
         }
      }
   }

   public File getPluginPath(Plugin p) {
      return (File)this.pluginPath.getOrDefault(p, null);
   }

   public PluginDescription getPluginDescription(String filename) {
      return this.getPluginDescription(new File(filename));
   }

   public Plugin loadPlugin(String filename) throws Exception {
      return this.loadPlugin(new File(filename));
   }

   private void initPlugin(PluginBase plugin, PluginDescription description, File dataFolder, File file) {
      plugin.init(this, this.server, description, dataFolder, file);
      plugin.onLoad();
   }

   public void enablePlugin(Plugin plugin) {
      if (plugin instanceof PluginBase && !plugin.isEnabled()) {
         this.server.getLogger().info(this.server.getLanguage().translateString("nukkit.plugin.enable", new String[]{plugin.getDescription().getFullName()}));
         ((PluginBase)plugin).setEnabled(true);
         this.server.getPluginManager().callEvent(new PluginEnableEvent(plugin));
      }

   }

   public void disablePlugin(Plugin plugin) {
      if (plugin instanceof PluginBase && plugin.isEnabled()) {
         this.server.getLogger().info(this.server.getLanguage().translateString("nukkit.plugin.disable", new String[]{plugin.getDescription().getFullName()}));
         this.server.getPluginManager().callEvent(new PluginDisableEvent(plugin));
         ((PluginBase)plugin).setEnabled(false);
      }

   }

   public Class<?> getClassByName(String name) {
      Class<?> cachedClass = (Class)this.classes.get(name);
      if (cachedClass != null) {
         return cachedClass;
      } else {
         Iterator var3 = this.classLoaders.values().iterator();

         while(var3.hasNext()) {
            SourcePluginClassLoader loader = (SourcePluginClassLoader)var3.next();

            try {
               cachedClass = loader.findClass(name, false);
            } catch (ClassNotFoundException var6) {
               this.server.getLogger().error(var6.toString());
            }

            if (cachedClass != null) {
               return cachedClass;
            }
         }

         return null;
      }
   }

   public void setClass(String name, Class<?> clazz) {
      if (!this.classes.containsKey(name)) {
         this.classes.put(name, clazz);
      }

   }

   private void removeClass(String name) {
      this.classes.remove(name);
   }
}