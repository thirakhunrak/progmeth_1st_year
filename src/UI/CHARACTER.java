package UI;

public enum CHARACTER {
	DRAGONGLY(ClassLoader.getSystemResource("image/character/dragonfly.png").toString(),
			ClassLoader.getSystemResource("image/character/dragonfly.png").toString(), "DRAGONFLY",
			ClassLoader.getSystemResource("image/poison/dragonfly_P.png").toString()),

	LADYBUG(ClassLoader.getSystemResource("image/character/ladybug.png").toString(),
			ClassLoader.getSystemResource("image/character/ladybug.png").toString(), "LADYBUG",
			ClassLoader.getSystemResource("image/poison/ladybug_P.png").toString()),

	WORM(ClassLoader.getSystemResource("image/character/worm.png").toString(),
			ClassLoader.getSystemResource("image/character/worm.png").toString(), "WORM",
			ClassLoader.getSystemResource("image/poison/worm_P.png").toString()),

	BEETLE(ClassLoader.getSystemResource("image/character/beetle.png").toString(),
			ClassLoader.getSystemResource("image/character/beetle.png").toString(), "BEETLE",
			ClassLoader.getSystemResource("image/poison/beetle_P.png").toString());

	private String urlCharacter;
	private String urlLife;
	private String typeCharacter;
	private String freezeCharacter;

	private CHARACTER(String urlCharacter, String urlLife, String typeCharacter, String freezeCharacter) {
		this.urlCharacter = urlCharacter;
		this.urlLife = urlLife;
		this.typeCharacter = typeCharacter;
		this.freezeCharacter = freezeCharacter;
	}

	public String geturl() {
		return this.urlCharacter;
	}

	public String getUrlLife() {
		return this.urlLife;
	}

	public String gettypeVehicle() {
		return this.typeCharacter;
	}

	public String getfreezVehicle() {
		return this.freezeCharacter;
	}
}
