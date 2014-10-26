package au.moodflip.cardgame.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.PlaylistItem;

@Service(value="playlistManager")
@Transactional
public class PlaylistManagerDbImpl implements PlaylistManager {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void add(Playlist playlist) {
		sessionFactory.getCurrentSession().save(playlist);
	}

	@Override
	public Playlist get(long userId) {
		return (Playlist)sessionFactory.getCurrentSession().get(Playlist.class, userId);
	}

	/** adds item to playlist belonging to userId
	 *  Creates a new playlist for user if one doesn't exist and also adds the item
	 *  @param item - just set the cardId
	 */
	@Override
	public void appendItem(PlaylistItem item, long userId) {
		if (userId != 0){
			Playlist pl = get(userId);
			if (pl != null){ // playlist exists in db
				List<PlaylistItem> items = pl.getPlaylistItems();
				items.add(item);
				item.setPlaylist(pl);
				sessionFactory.getCurrentSession().merge(pl);
			}else{ // playlist doesn't exist in db
				List<PlaylistItem> items = new ArrayList<PlaylistItem>();
				Playlist playlist = new Playlist(userId, items);
				items.add(item);
				item.setPlaylist(playlist);
				add(playlist);
			}
		}
	}

	@Override
	public void update(Playlist playlist) {
		sessionFactory.getCurrentSession().merge(playlist);
	}

}
