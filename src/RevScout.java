import java.util.List;
import java.util.Random;

import org.dreambot.api.methods.container.impl.bank.BankLocation;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.map.Area;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.prayer.Prayer;
import org.dreambot.api.methods.tabs.Tab;
import org.dreambot.api.methods.world.World;
import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.wrappers.interactive.GameObject;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.interactive.Player;
import org.dreambot.api.wrappers.items.Item;

@ScriptManifest(author = "Awesomemite", category = Category.WOODCUTTING, description = "REV SCOUT", image = "", name = "REV SCOUT", version = 10.0)
public class RevScout extends AbstractScript {

	Area lumbySpawn = new Area(new Tile(3217, 3225), new Tile(3225, 3225), new Tile(3226, 3210), new Tile(3217, 3211));

	private int getState() {
		Area inside5 = new Area(new Tile(3255, 10169), new Tile(3256, 10169), new Tile(3256, 10166),
				new Tile(3255, 10166));

		if (inside5.contains(getLocalPlayer())) {
			return 1;
		}

		if (lumbySpawn.contains(getLocalPlayer())) {
			return 0;
		}

		return 1;
	}

	public void onStart() {
		getMouse().getMouseSettings().setWordsPerMinute(300);
	}

	@Override
	public int onLoop() {
		switch (getState()) {
		case 0:
			walkToRevs();
			break;
		case 1:
			scouting();
			break;
		}

//		List<Player> allPlayer = getPlayers().all();
//		for (int i = 0; i < allPlayer.size(); i++) {
//			if (allPlayer.get(i).getName().equals("Shottaaz")) {
//				log("ID " + allPlayer.get(i).getAnimation());
//			}
//		}
//
		sleep(500);

		return 0;
	}

	Filter<Item> burningAmmy = new Filter<Item>() {
		public boolean match(Item a) {
			if (a.getName().equals("Burning amulet(5)") || a.getName().equals("Burning amulet(4)")
					|| a.getName().equals("Burning amulet(3)") || a.getName().equals("Burning amulet(2)")
					|| a.getName().equals("Burning amulet(1)")) {
				return true;
			}

			return false;
		}
	};

	public void rubAmmy() {
		getTabs().open(Tab.INVENTORY);
		sleep(1000);
		if (getInventory().contains("Burning amulet(5)")) {
			getInventory().interact("Burning amulet(5)", "Rub");
		} else if (getInventory().contains("Burning amulet(4)")) {
			getInventory().interact("Burning amulet(4)", "Rub");
		} else if (getInventory().contains("Burning amulet(3)")) {
			getInventory().interact("Burning amulet(3)", "Rub");
		} else if (getInventory().contains("Burning amulet(2)")) {
			getInventory().interact("Burning amulet(2)", "Rub");
		} else if (getInventory().contains("Burning amulet(1)")) {
			getInventory().interact("Burning amulet(1)", "Rub");
		}
	}

	public void walkToEastWall() {
		sleep(3000);

		getPrayer().toggle(true, Prayer.PROTECT_FROM_MAGIC);
		sleep(1000);

		Area one = new Area(new Tile(3039, 3836), new Tile(3041, 3836), new Tile(3041, 3834), new Tile(3039, 3834));
		Area two = new Area(new Tile(3047, 3825), new Tile(3045, 3825), new Tile(3045, 3823), new Tile(3047, 3823));
		Area three = new Area(new Tile(3063, 3827), new Tile(3061, 3827), new Tile(3061, 3825), new Tile(3063, 3825));
		Area four = new Area(new Tile(3074, 3821), new Tile(3072, 3821), new Tile(3072, 3819), new Tile(3074, 3819));
		Area five = new Area(new Tile(3089, 3817), new Tile(3087, 3817), new Tile(3087, 3815), new Tile(3089, 3815));
		Area six = new Area(new Tile(3103, 3820), new Tile(3101, 3820), new Tile(3101, 3818), new Tile(3103, 3818));
		Area seven = new Area(new Tile(3115, 3827), new Tile(3117, 3827), new Tile(3117, 3825), new Tile(3115, 3825));
		Area eight = new Area(new Tile(3126, 3832), new Tile(3126, 3834), new Tile(3128, 3834), new Tile(3128, 3832));

		walkHelper(one);
		walkHelper(two);
		walkHelper(three);
		walkHelper(four);
		walkHelper(five);
		walkHelper(six);
		walkHelper(seven);
		walkHelper(eight);

		getGameObjects().closest("Cavern").interact("Enter");
		sleep(1000);

		// walk inside cave here@@@@
		Area inside1 = new Area(new Tile(3248, 10225), new Tile(3250, 10225), new Tile(3250, 10223),
				new Tile(3248, 10223));
		Area inside2 = new Area(new Tile(3259, 10215), new Tile(3257, 10215), new Tile(3258, 10213),
				new Tile(3260, 10213));
		Area inside3 = new Area(new Tile(3258, 10199), new Tile(3256, 10199), new Tile(3256, 10197),
				new Tile(3257, 10197));
		Area inside4 = new Area(new Tile(3254, 10184), new Tile(3256, 10184), new Tile(3256, 10182),
				new Tile(3254, 10182));
		Area inside5 = new Area(new Tile(3255, 10169), new Tile(3256, 10169), new Tile(3256, 10166),
				new Tile(3255, 10166));

		walkHelper(inside1);
		walkHelper(inside2);
		walkHelper(inside3);
		walkHelper(inside4);
		walkHelper(inside5);
	}

	int[] worldsList = new int[] { 302, 303, 304, 305, 306, 307, 309, 310, 311, 312, 313, 314, 315, 317, 318, 319, 320,
			321, 322, 323, 325, 327, 328, 329, 330, 331, 332, 333, 334, 336, 338, 339, 340, 341, 342, 343, 344, 346,
			347, 348, 350, 351, 352, 354, 355, 356, 357, 358, 359, 360, 362, 367, 368, 369, 370, 374, 375, 376, 377,
			378, 386, 387, 388, 389, 390, 395, 421, 422 };

	public void scouting() {
		Random rn = new Random();
		int newWorld = worldsList[rn.nextInt(worldsList.length)];

		getWorldHopper().hopWorld(newWorld);
		sleep(6000);
		List<Player> allPlayer = getPlayers().all();
		int skulls = 0;
		String worldSkullInfo = "World " + newWorld + " has ";
		String cbInfo = "Combat levels: ";
		String newWeapons = "This player has new weapon: ";
		boolean found = false;

		for (int i = 0; i < allPlayer.size(); i++) {
			if (allPlayer.get(i).isSkulled()) {
				skulls++;
				cbInfo += allPlayer.get(i).getLevel() + " ";
			} else {
				// 245 and 7200
				// 426 424
				for (int j = 0; j < 5; j++) {
					if (allPlayer.get(i).getAnimation() == 245 || allPlayer.get(i).getAnimation() == 7200
							|| allPlayer.get(i).getAnimation() == 426 || allPlayer.get(i).getAnimation() == 424) {
						found = true;
						if (!newWeapons.contains(allPlayer.get(i).getName())) {
							newWeapons += allPlayer.get(i).getName() + " " + allPlayer.get(i).getLevel() + " ";
						}
					}
					sleep(100);
				}
			}

		}
		worldSkullInfo = worldSkullInfo + skulls + " skulls.";
		if (skulls > 0) {
			getKeyboard().type("/" + worldSkullInfo);
			sleep(1000);
			getKeyboard().type("/" + cbInfo);
		}
		if (found) {
			getKeyboard().type("/" + "World " +  newWorld + " has new weapons");
			sleep(1000);
			getKeyboard().type("/" + newWeapons);

		}

		sleep(11000);
	}

	public void walkHelper(Area area) {
		while (!area.contains(getLocalPlayer())) {
			getWalking().walk(area.getRandomTile());
			sleep(1000);
		}
		sleep(1000);
	}

	public void walkToRevs() {
		getTabs().open(Tab.INVENTORY);
		sleep(500);
		if (!getInventory().contains(burningAmmy)) {
			stop();
		}
		rubAmmy();
		sleep(1500);
		getKeyboard().type("3");
		sleep(1500);
		getKeyboard().type("1");
		sleep(1500);

		walkToEastWall();
		// scouting();
		// }
	}

}
