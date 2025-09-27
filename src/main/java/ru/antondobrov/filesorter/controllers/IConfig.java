package ru.antondobrov.filesorter.controllers;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import ru.antondobrov.filesorter.model.SorterConfig;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY,
        property = "@config_type")
@JsonSubTypes({@JsonSubTypes.Type(value = SorterConfig.class, name = "file_sorter")})
public interface IConfig extends IRulesConfig, IStartDirectoryConfig, IAdditionalSettingsConfig {

}
