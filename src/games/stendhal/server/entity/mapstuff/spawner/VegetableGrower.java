/* $Id$ */
/***************************************************************************
 *                      (C) Copyright 2003 - Marauroa                      *
 ***************************************************************************
 ***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/
package games.stendhal.server.entity.mapstuff.spawner;

import games.stendhal.server.core.engine.StendhalRPWorld;
import games.stendhal.server.core.events.UseListener;
import games.stendhal.server.entity.RPEntity;
import games.stendhal.server.entity.item.Item;
import games.stendhal.server.entity.player.Player;
import marauroa.common.game.RPObject;

/**
 * A growing carrot which can be picked.
 * 
 * @author hendrik
 */
public class VegetableGrower extends GrowingPassiveEntityRespawnPoint implements
		UseListener {
	private String vegetableName;

	public VegetableGrower(RPObject object, String name) {
		super(object, "items/grower/" + name + "_grower", "Pick", 1, 1, 1);
		vegetableName = name;
		update();
	}

	public VegetableGrower(String name) {
		super("items/grower/" + name + "_grower", "Pick", 1, 1, 1);
		vegetableName = name;
	}

	@Override
	public String describe() {
		String text;
		switch (getRipeness()) {
		case 0:
			text = "You see a planted " + vegetableName + " seed.";
			break;
		case 1:
			text = "You see a ripe " + vegetableName + ".";
			break;
		default:
			text = "You see an unripe " + vegetableName + ".";
			break;
		}
		return text;
	}

	/**
	 * Is called when a player tries to harvest this item.
	 * @param entity that tries to harvest
	 * @return true on success
	 */
	public boolean onUsed(RPEntity entity) {
		if (entity.nextTo(this)) {
			if (getRipeness() == 1) {
				onFruitPicked(null);
				Item grain = StendhalRPWorld.get().getRuleManager().getEntityManager().getItem(
						vegetableName);
				entity.equip(grain, true);
				return true;
			} else if (entity instanceof Player) {
				((Player) entity).sendPrivateText("This " + vegetableName
						+ " is not yet ripe enough to pick.");

			}
		}
		return false;
	}

}
