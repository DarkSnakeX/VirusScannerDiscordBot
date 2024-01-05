# VirusScannerDiscordBot
A discord bot that uses the VirusTotal API to check if links may be malicious.

# Description
This bot analyzes all the messages that users sends with the slash command and it analyzes it using
the Virustotal API, as soon as there is at least an indication that the URL/link you passed may be malicious.
A message is sent about it, otherwise it sends another message warning of its apparent reliability.

# Requirements
- Have a valid Virustotal API.
- Have a valid Discord token to create bots.

# Warning
This bot is purely educational to teach about cybersecurity on discord and the use of the Virustotal API in Java.
Also remember that when using the Virustotal API, all URLs and files that are scanned are stored PUBLICLY in the Virustotal database, so be careful.