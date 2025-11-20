import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlayerTest {

	@Test
	void newPlayerTest() {
		Player sarah=new Player("Sarah");
		assertEquals(sarah.getDay(),1);
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getScore(),0);
	}
	@Test
	void oldPlayerTest() {
		Player sarah=new Player("Sarah",3,600);
		assertEquals(sarah.getDay(),3);
		assertEquals(sarah.getName(),"Sarah");
		assertEquals(sarah.getScore(),600);
	}
	@Test
	void scoreTest() {
		Player sarah=new Player("Sarah",3,600);
		sarah.addScore(200);
		assertEquals(sarah.getScore(),800);
	}
	@Test
	void daysTest() {
		Player sarah=new Player("Sarah",3,600);
		sarah.nextDay();
		assertEquals(sarah.getDay(),4);
	}

}
