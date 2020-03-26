CREATE TYPE sendType AS ENUM ('Emergency', 'Update', 'Geofencing');
CREATE TYPE transport AS ENUM ('*', 'HTTP', 'TCP', 'SMS');
CREATE TYPE replyChannel AS ENUM ('Nothing', 'Call', 'SMS', 'Message');

CREATE TABLE configuration (
    name varchar(255) NOT NULL,
    separators varchar(255),
    idIdx integer NOT NULL,
    idLength integer NOT NULL,
    idRegex varchar(255),
    fieldCount integer NOT NULL,
    sendType sendType NOT NULL,
    transport transport NOT NULL,
    replyChannel replyChannel NOT NULL,
    messageHandle varchar(255) NOT NULL,
    latitudeIdx integer NOT NULL,
    latitudeRegex varchar(255),
    longitudeIdx integer NOT NULL,
    longitudeRegex varchar(255),
    isDMS boolean NOT NULL,
    description text,
    optionalData text
);


