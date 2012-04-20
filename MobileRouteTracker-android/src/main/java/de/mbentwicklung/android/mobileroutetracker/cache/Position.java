package de.mbentwicklung.android.mobileroutetracker.cache;

/**
 * Position
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class Position {

	private final Integer id;
	private final Double lon;
	private final Double lat;
	private final Double ele;
	private final Long time;

	/**
	 * @param id
	 * @param lon
	 * @param lat
	 * @param ele
	 * @param time
	 */
	public Position(Integer id, Double lon, Double lat, Double ele, Long time) {
		super();
		this.id = id;
		this.lon = lon;
		this.lat = lat;
		this.ele = ele;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public Double getLon() {
		return lon;
	}

	public Double getLat() {
		return lat;
	}

	public Double getEle() {
		return ele;
	}

	public Long getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "Position [id=" + id + ", lon=" + lon + ", lat=" + lat + ", ele=" + ele + ", time="
				+ time + "]";
	}

}
