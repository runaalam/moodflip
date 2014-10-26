package au.moodflip.cardgame.service;

import au.moodflip.cardgame.model.Playlist;
import au.moodflip.cardgame.model.PlaylistItem;

public interface PlaylistManager {
	public void add(Playlist playlist);
	public void appendItem(PlaylistItem item, long userId);
	public void update(Playlist playlist);
	public Playlist get(long userId);
}
