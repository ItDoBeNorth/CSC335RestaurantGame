import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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
	void scoreTest() {
		Player sarah=new Player("Sarah");
		sarah.addScore(200);
		sarah.addMoney(10);
		assertEquals(sarah.getScore(),200);
		assertEquals(sarah.getMoney(),10);
	}
	@Test
	void daysTest() {
		Player sarah=new Player("Sarah");
		sarah.nextDay();
		assertEquals(sarah.getDay(),1);
		sarah.addMoney(15);
		assertEquals(sarah.getMoney(),15);
		sarah.addMoney(5);
		assertEquals(sarah.getMoney(),20);
	}
	@Test
	void milestonesTest() {
		Player sarah=new Player("Sarah");
		for(int c=0;c<10;c++) {
			sarah.addCustomerServed();
		}
		for(int a=0;a<10;a++) {
			sarah.addPerfectAccuracy();
		}
		for(int t=0;t<5;t++) {
			sarah.addPerfectTiming();;
		}

		assertEquals(sarah.milestones().size(),3);
		
		sarah.resetPerfectAccuracy();
		sarah.resetPerfectTiming();
		
		assertEquals(sarah.milestones().size(),0);
		
		for(int a=0;a<5;a++) {
			sarah.addPerfectAccuracy();
		}
		for(int t=0;t<3;t++) {
			sarah.addPerfectTiming();;
		}
		assertEquals(sarah.milestones().size(),2);
	}
	
	@Test
	void equalsTest() {
		Player sarah=new Player("Sarah");
		Player sarah2=new Player("Sarah");
		Player otherPlayer=new Player("otherPlayer");
		Player nonPlayer = null;
		assertTrue(sarah.equals(sarah));
		assertTrue(sarah.equals(sarah2));
		assertFalse(sarah.equals(otherPlayer));
		assertFalse(sarah.equals(nonPlayer));
	}

}
