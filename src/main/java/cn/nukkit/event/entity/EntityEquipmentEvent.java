package cn.nukkit.event.entity;

import cn.nukkit.entity.Entity;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.HandlerList;
import cn.nukkit.item.Item;

public class EntityEquipmentEvent extends EntityEvent implements Cancellable {

  public static final HandlerList handlers = new HandlerList();

  public static HandlerList getHandlers() {
      return handlers;
  }

  private final Item oldItem;
  private Item newItem;
  private int hotbarSlot, inventorySlot;

  public EntityEquipmentEvent(Entity entity, Item oldItem, Item newItem, int hotbarSlot, int inventorySlot) {
    this.oldItem = oldItem;
    this.newItem = newItem;
    this.hotbarSlot = hotbarSlot;
    this.entity = entity;
    this.inventorySlot = inventorySlot;
  }

  public Item getOldItem() {
    return oldItem;
  }

  public Item getNewItem() {
    return newItem;
  }

  public void setNewItem(Item item) {
    newItem = item;
  }

  public int getInventorySlot() {
    return inventorySlot;
  }

  public void setInventorySlot(int slot) {
    inventorySlot = slot;
  }

  public int getHotbarSlot() {
    return hotbarSlot;
  }

  public void setHotbarSlot(int slot) {
    hotbarSlot = slot;
  }
}
