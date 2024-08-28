package personal.learning.app.mysql.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "instructor_detail")
public class InstructorDetailMsq {
	
	@Id
	private BigInteger id;
	
	@Column(name = "youtube_channel")
	private String youtubeChannel;
	
	@Column(name = "channel_description")
	private String channelDescription;
	
	@Column(name = "no_of_subscriber")
	private BigInteger noOfSubscribers;
	
	@Column(name = "instructor_id")
	private BigInteger instructorId;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getYoutubeChannel() {
		return youtubeChannel;
	}

	public void setYoutubeChannel(String youtubeChannel) {
		this.youtubeChannel = youtubeChannel;
	}

	public String getChannelDescription() {
		return channelDescription;
	}

	public void setChannelDescription(String channelDescription) {
		this.channelDescription = channelDescription;
	}

	public BigInteger getNoOfSubscribers() {
		return noOfSubscribers;
	}

	public void setNoOfSubscribers(BigInteger noOfSubscribers) {
		this.noOfSubscribers = noOfSubscribers;
	}

	public BigInteger getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(BigInteger instructorId) {
		this.instructorId = instructorId;
	}

}
