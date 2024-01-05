package me.darksnakex;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.hc.core5.http.ParseException;
import java.io.IOException;
import java.util.*;
import static me.darksnakex.FunctionUrl.*;

public class SlashCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("analizar")) {
            event.deferReply().queue();
            if (event.getOption("url") != null) {
                String urlToCheck = Objects.requireNonNull(event.getOption("url")).getAsString();

                if (isValidUrl(urlToCheck)) {
                    try {
                        String result = checkUrl(urlToCheck);
                        if(result == null){
                            event.getHook().sendMessage("Error, this link was not found in the database, please try again in 1 minute.").queue();
                        }else{
                            event.getHook().sendMessage(getVirusTotalSummary(result)).queue();
                        }
                    } catch (IOException | ParseException e) {
                        event.getHook().sendMessage("Error trying to analyze: " + e.getMessage()).queue();
                    }
                } else {
                    event.reply("The URL provided is invalid. Please enter a valid URL to analyze.").queue();
                }
            } else {
                event.reply("You must provide a URL to analyze.").queue();
            }
        }
    }



}