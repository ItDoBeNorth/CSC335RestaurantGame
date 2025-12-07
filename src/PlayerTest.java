import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void newPlayerTest() {
		Player sarah=new Player("Sarah");
		assertEquals(sarah.getDay(),0);
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getScore(),0);
	}
	@Test
	void oldPlayerTest() {
		Player sarah=new Player("Sarah",3,600,10);
		assertEquals(sarah.getDay(),3);
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getScore(),600);
		assertEquals(sarah.getMoney(),10);

	}
	@Test
	void scoreTest() {
		Player sarah=new Player("Sarah",3,600,10);
		sarah.addScore(200);
		assertEquals(sarah.getScore(),800);
		assertEquals(sarah.getMoney(),10);
	}
	@Test
	void daysTest() {
		Player sarah=new Player("Sarah",3,600,15);
		sarah.nextDay();
		assertEquals(sarah.getDay(),4);
		assertEquals(sarah.getMoney(),15);
		sarah.addMoney(2);
		assertEquals(sarah.getMoney(),17);
	}
	
	@Test
	void equalsTest() {
		Player sarah=new Player("Sarah",3,600,15);
		Player sarah2=new Player("Sarah",3,600,15);
		Player otherPlayer=new Player("otherPlayer",3,600,15);
		Player nonPlayer = null;
		assertTrue(sarah.equals(sarah));
		assertTrue(sarah.equals(sarah2));
		assertFalse(sarah.equals(otherPlayer));
		assertFalse(sarah.equals(nonPlayer));
	}

}
