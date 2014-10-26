package au.moodflip.cardgame.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="Playlists")
public class Playlist {
	public Playlist(){}
	public Playlist(long userId, List<PlaylistItem> playlistItems){
		this.userId = userId;
		this.playlistItems = playlistItems;
	}
	
	@Id
	@Column(name="userId")
	public long getUserId() { return userId; }
	public void setUserId(long userId) { this.userId = userId; }
	private long userId;
	
//	@OneToOne
//    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "cg_user_id")
//	public CgUser getUser() { return user; }
//	public void setUser(CgUser user) { this.user = user; }
//	private CgUser user;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="playlist_idFK")
	@IndexColumn(name="play_index")
	public List<PlaylistItem> getPlaylistItems() { return playlistItems; }
	public void setPlaylistItems(List<PlaylistItem> playlistItems) { this.playlistItems = playlistItems; }
	private List<PlaylistItem> playlistItems;
}
