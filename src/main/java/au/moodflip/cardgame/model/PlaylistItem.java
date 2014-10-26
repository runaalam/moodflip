package au.moodflip.cardgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PlaylistItems")
public class PlaylistItem {
	public PlaylistItem(){ }
	public PlaylistItem(long cardId){ 
		this.cardId = cardId;
	}
	public PlaylistItem(long cardId, Playlist playlist){ 
		this.cardId = cardId;
		this.playlist = playlist;
	}
	
	@Id
	@GeneratedValue
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	private long id;
	
	@ManyToOne
	@JoinColumn(name="playlist_idFK", updatable=false, insertable=false)
	public Playlist getPlaylist() {	return playlist; }
	public void setPlaylist(Playlist playlist) { this.playlist = playlist; }
	private Playlist playlist;
	
	@Column(name="cardId")
	public long getCardId() { return cardId; }
	public void setCardId(long cardId) { this.cardId = cardId; }
	private long cardId;
}
