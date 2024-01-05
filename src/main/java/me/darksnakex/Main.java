package me.darksnakex;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;


public class Main extends ListenerAdapter {
    static Dotenv dotenv = Dotenv.load();
    static String VIRUS_TOTAL_API_KEY = dotenv.get("VIRUS_TOTAL_API_KEY");
    static String BOT_TOKEN = dotenv.get("BOT_TOKEN");

    public static void main(String[] args) {

        JDA jda = JDABuilder.createDefault(BOT_TOKEN)
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new Main(), new SlashCommand())
                .setActivity(Activity.customStatus("Trying to protect you."))
                .setStatus(OnlineStatus.DO_NOT_DISTURB)
                .build();


        jda.upsertCommand("analyze", "Enter a link/url that you want to analyze to see if it is malicious.")
                .addOption(OptionType.STRING, "url", "Enter the link/url to scan")
                .queue();


    }


}