package au.moodflip.test.cardgame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.PlaylistItem;
import au.moodflip.cardgame.service.PlaylistManager;
import au.moodflip.personalisation.model.User;
import au.moodflip.personalisation.service.UserManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-persistence-context.xml" })
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class PlaylistManagerTest {
	@Autowired
	private PlaylistManager playlistManager;
	@Autowired
	private UserManager userManager;
	
	@Test
	public void testAdd(){
		User user = userManager.getUserByUsername("user");
		User admin = userManager.getUserByUsername("admin");
		List<PlaylistItem> playlistItems = new ArrayList<PlaylistItem>();
		Playlist playlist = new Playlist(user.getId(), playlistItems);
		playlistItems.add(new PlaylistItem(1L));
		playlistItems.add(new PlaylistItem(2L));
		for (PlaylistItem item : playlistItems){
			item.setPlaylist(playlist);
		}
		
		playlistManager.add(playlist);
		
		Playlist pl = playlistManager.get(user.getId());
		System.out.println("Playlist contains:");
		for (PlaylistItem i : pl.getPlaylistItems()){
			System.out.println(i.getCardId());
		}
		
		pl.getPlaylistItems().add(new PlaylistItem(3L, pl));
		playlistManager.update(pl);
		System.out.println("Playlist updated:");
		for (PlaylistItem i : pl.getPlaylistItems()){
			System.out.println(i.getCardId());
		}
		
		playlistManager.appendItem(new PlaylistItem(4L), user.getId());
		playlistManager.appendItem(new PlaylistItem(4L), admin.getId());
	}
}
