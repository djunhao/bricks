package org.northstar.bricks.dao;

import org.northstar.bricks.domain.Card;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CardDao {
	public List<Card> list() {

		// Return a mock set as an example.
		return Arrays.asList(new Card("hello", "how are you", new Date(),
				"dhanji"), new Card("yoyo", "how are you dude?", new Date(),
				"zac"));
	}

}
