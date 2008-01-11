package games.stendhal.server.maps.semos.townhall;

import games.stendhal.server.core.config.ZoneConfigurator;
import games.stendhal.server.core.engine.StendhalRPZone;
import games.stendhal.server.core.pathfinder.FixedPath;
import games.stendhal.server.core.pathfinder.Node;
import games.stendhal.server.entity.npc.SpeakerNPC;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MayorNPC implements ZoneConfigurator {
	/**
	 * Configure a zone.
	 *
	 * @param	zone		The zone to be configured.
	 * @param	attributes	Configuration attributes.
	 */
	public void configureZone(StendhalRPZone zone, Map<String, String> attributes) {
		buildSemosTownhallAreaMayor(zone);
	}

	/**
	 * Adds a Mayor to the townhall who gives out daily quests.
	 * @param zone zone to be consigured with this
	 */
	private void buildSemosTownhallAreaMayor(StendhalRPZone zone) {
		// We create an NPC
		SpeakerNPC npc = new SpeakerNPC("Mayor Sakhs") {

			@Override
			protected void createPath() {
				List<Node> nodes = new LinkedList<Node>();
				nodes.add(new Node(13, 3));
				nodes.add(new Node(19, 3));
				setPath(new FixedPath(nodes, true));
			}

			@Override
			protected void createDialog() {
				addGreeting("Welcome citizen! Do you need #help?");
				addJob("I'm the mayor of Semos village.");
				addHelp("You will find a lot of people in Semos that offer you help on different topics.");
				addGoodbye("Have a good day and enjoy your stay!");
			}
		};

		npc.setEntityClass("mayornpc");
		npc.setPosition(13, 3);
		npc.initHP(100);
		zone.add(npc);
	}
}
