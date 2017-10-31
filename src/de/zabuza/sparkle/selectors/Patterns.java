package de.zabuza.sparkle.selectors;

/**
 * Utility class that provides regex patterns.
 * 
 * @author Zabuza {@literal <zabuza.dev@gmail.com>}
 *
 */
public final class Patterns {
	/**
	 * Regex pattern for extracting the name and the content of a clan chat message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_CLAN = "(.+) \\(Clantelepathie\\):\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a direct chat
	 * message that comes from an user.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_DIRECT_FROM_USER = "(.+):\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a neutral direct
	 * chat message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_DIRECT_NEUTRAL = "(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a global chat
	 * message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_GLOBAL = "(.+) \\(Welt \\d{1,2}\\):\\s?(.*)";

	/**
	 * Regex pattern for extracting the name and the content of a group chat
	 * message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_GROUP = "(.+) \\(Gruppentelepathie\\):\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of an info chat
	 * message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_INFO = "(?s)(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a scream chat
	 * message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_SCREAM = "(.+) schreit:\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a neutral whisper
	 * chat message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_WHISPER_NEUTRAL = "(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a whisper chat
	 * message when receiving.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_WHISPER_RECEIVING = "(.+) fl�stert zu dir:\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a whisper chat
	 * message when sending.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_WHISPER_SENDING = "Du fl�sterst zu (.+):\\s?(.*)";
	/**
	 * Regex pattern for extracting the name and the content of a world-say chat
	 * message.
	 */
	public final static String CHAT_MESSAGE_NAME_CONTENT_WORLDSAY = "(?s)(.*)";
	/**
	 * Regex pattern for an integer.
	 */
	public final static String INTEGER = "-?\\d+";
	/**
	 * Regex pattern for extracting the name, level and end date of the currently
	 * trained skill.
	 */
	public final static String ITEM_SKILL_CUR_TRAINED_SKILL = "Du trainierst gerade (.+) auf Stufe (\\d+)\\. Das Training ist in (?:(\\d+) Tagen )?(?:(\\d+) Stunden )?(?:(\\d+) Minuten )?fertig\\. Training abbrechen";
	/**
	 * Pattern for the activation anchor in the special skill dialog.
	 */
	public final static String ITEM_SKILL_SPECIAL_SKILL_DIALOG_ACTIVATION = "Anwenden";
	/**
	 * Regex pattern for extracting the maximal amount of life points.
	 */
	public final static String PLAYER_MAX_LIFE_POINTS = "\\(\\d+/(\\d+)\\)";
	/**
	 * Coordinate pattern for the wiki content. The first group holds the x
	 * coordinate, the second group holds the y coordinate.
	 */
	public final static String WIKI_CONTENT_COORDINATES = "(-?\\d+),(-?\\d+);?";

	/**
	 * Utility class. No implementation.
	 */
	private Patterns() {

	}
}
