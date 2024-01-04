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
                            event.getHook().sendMessage("Error, no se ha encontrado ese enlace en la base de datos, vuelve a intentarlo en 1 minuto.").queue();
                        }else{
                            event.getHook().sendMessage(getVirusTotalSummary(result)).queue();
                        }
                    } catch (IOException e) {
                        event.getHook().sendMessage("Error al analizar la URL: " + e.getMessage()).queue();
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    event.reply("La URL proporcionada no es válida. Por favor, ingresa una URL válida para analizar.").queue();
                }
            } else {
                event.reply("Debes proporcionar una URL para analizar.").queue();
            }
        }
    }



}