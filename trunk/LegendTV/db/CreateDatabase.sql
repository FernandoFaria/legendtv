CREATE DATABASE IF NOT EXISTS LegendTV;

-- Table for storing TV stations -- this is not the same thing as a "channel",
-- per se. Rather, a station is what a channel provides, while the channel
-- itself is merely where the station is located. Thus, the same station may
-- be broadcast on two or more channels.
CREATE TABLE IF NOT EXISTS Stations
(
	-- Primary Key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	
	-- The unique identifier the data source uses for this station.
	SourceKey		VARCHAR(12)		NOT NULL,
	
	-- Mnemonic or FCC recognized call sign for long name of a station. Digital
	-- stations will carry the suffix of ÒDTÓ.
	CallSign		VARCHAR(10)		NOT NULL,
	
	-- Long name of a station.
	LongName		VARCHAR(40)	NOT NULL,
	
	-- Optional. Network, cable or broadcasting group with which a station is
	-- associated.
	Affiliate		VARCHAR(25)
)
TYPE=innodb;

-- Table for storing the channels that are associated with particular Stations.
-- As explained in the comment for the Stations table, a channel and a station
-- are two separate things -- a channel is the medium over which a station is
-- delivered.
CREATE TABLE IF NOT EXISTS Channels
(
	-- Primary Key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	
	-- Station to which this channel belongs
	FK_StationID	INT				NOT NULL,
	
	-- Line-up to which this channel belongs
	FK_LineupID		INT				NOT NULL
)
TYPE=innodb;

-- Table for channels that can be tuned over the air or via cable by a
-- traditional, non-HD television.
CREATE TABLE IF NOT EXISTS AnalogChannels
(
	-- Primary Key (matches PK_ID of corresponding entry in Channels)
	PK_ID			INT				NOT NULL PRIMARY KEY,
	
	-- Channel number on the analog dial (2-125); higher with a cable box
	ChannelNumber	SMALLINT		NOT NULL
		
)
TYPE=innodb;

-- Table for channels that are tuned using a satellite dish.
CREATE TABLE IF NOT EXISTS SatelliteChannels
(
	-- Primary Key (matches PK_ID of corresponding entry in Channels)
	PK_ID			INT				NOT NULL PRIMARY KEY,
	
	-- Dish position (i.e. degrees)
	Position		INT				NOT NULL,
	
	-- Satellite channel number
	ChannelNumber	INT
)
TYPE=innodb;

-- Table for storing digital / high definition channels.
CREATE TABLE IF NOT EXISTS HDChannels
(
	-- Primary Key (matches PK_ID of corresponding entry in Channels)
	PK_ID			INT				NOT NULL PRIMARY KEY,
	
	-- Major channel number (typically, the analog channel number that this
	-- digital channel corresponds to)
	MajorNumber		VARCHAR(5)		NOT NULL,
	
	-- Minor channel number (i.e. sub-service of a particular provider,
	-- numbered from 1)
	MinorNumber		SMALLINT
)
TYPE=innodb;

-- Table for storing channel line-ups -- collections of channels for a
-- particular location and TV provider.
CREATE TABLE IF NOT EXISTS Lineups
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	
	-- The unique identifier the data source uses for this line-up
	SourceKey		VARCHAR(10)		NOT NULL,
	
	-- The data source that this line-up corresponds to
	FK_DataSourceID	INT				NOT NULL,
	
	-- Name of this line-up
	Name			VARCHAR(20)		NOT NULL,
	
	-- Frequency map for this line-up (FIXME: Normalize me)
	FrequencyMap	VARCHAR(255)	NOT NULL
)
TYPE=innodb;

-- Table for storing information about data sources -- providers of TV listing
-- information and line-up information.
CREATE TABLE IF NOT EXISTS DataSources
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	
	-- Name of the data source (DataDirect, etc.)
	Name			VARCHAR(50)		NOT NULL,

	-- Java class name for the data source provider
	-- (i.e. org.legendtv.providers.DataDirect, etc.)
	ClassName		VARCHAR(255)	NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS TunerInputs
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	FK_TunerCardID	INT				NOT NULL,
	FK_LineupID		INT				NOT NULL,
	Name			VARCHAR(?)		NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS TunerCards
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)		NOT NULL,
	VideoDevice		VARCHAR(?)		NOT NULL,
	AudioDevice		VARCHAR(?)		NOT NULL,
	VBIDevice		VARCHAR(?)
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Ratings
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)		NOT NULL,
	FK_RatingTypeID	INT				NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS RatingTypes
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS ProgramToAdvisoriesMap
(
	FK_ProgramID	INT				NOT NULL PRIMARY KEY,
	FK_AdvisoryID	INT				NOT NULL PRIMARY KEY
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Schedules
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	FK_ProgramID	INT				NOT NULL,
	FK_StationID	INT				NOT NULL,
	Time			DATE?			NOT NULL,
	Duration		DOUBLE?			NOT NULL,
	FK_TvRatingID	INT				NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS ScheduleToFlagsMap
(
	FK_ProgramID	INT				NOT NULL PRIMARY KEY,
	FK_FlagID		INT				NOT NULL PRIMARY KEY
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Flags
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)		NOT NULL,
	FK_FlagGroupID	INT				NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS FlagGroups
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)		NOT NULL,
	FK_FlagTypeID	INT				NOT NULL,
	IsRequired		BIT				NOT NULL DEFAULT 0
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Advisories
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Value			VARCHAR(?)		NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS CrewMembers
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	FirstName		VARCHAR(?)		NOT NULL,
	LastName		VARCHAR(?)		NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS FlagTypes
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)		NOT NULL
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS ProgramToGenresMap
(
	FK_ProgramID	INT				NOT NULL PRIMARY KEY,
	FK_GenreID		INT				NOT NULL PRIMARY KEY,
	Relevance		INT
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Programs
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	SourceKey		?				NOT NULL,
	FK_TitleID		INT				NOT NULL,
	FK_SubtitleID	INT				NOT NULL,
	Description		?,
	OriginalAirDate	DATE
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Episodes
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	SourceSeriesID	VARCHAR(?),
	SyndEpisodeNum	INT
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Movies
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	FK_MpaaRatingID	?,
	StarRating		?,
	RunTime			?,
	Year			?
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS ProgramToCrewMap
(
	FK_ProgramID	INT				NOT NULL PRIMARY KEY,
	FK_CrewMemberID	INT				NOT NULL PRIMARY KEY,
	FK_CrewRoleID	INT				NOT NULL PRIMARY KEY
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS CrewRoles
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Titles
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)
)
TYPE=innodb;

CREATE TABLE IF NOT EXISTS Genres
(
	-- Primary key
	PK_ID			INT				NOT NULL AUTO_INCREMENT PRIMARY KEY,
	Name			VARCHAR(?)
)
TYPE=innodb;