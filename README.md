# VirusScannerDiscordBot
Un bot de discord que utiliza la API de VirusTotal para comprobar si los enlaces pueden ser maliciosos.

# Descripción
Este bot analiza todos los mensajes que envían los usuarios y cuando encuentra un enlace lo analiza usando
la API de Virustotal, tan pronto como haya al menos una indicación de que la URL/enlace que ha pasado puede ser malicioso
se envía un mensaje al respecto, en caso contrario envía otro mensaje avisando de su aparente fiabilidad.

# Requisitos
- Disponer de una API de Virustotal válida.
- Tener un token de Discord válido para crear bots.

# Advertencia
Este bot es puramente educativo para enseñar sobre ciberseguridad en discord y el uso de la API Virustotal en Java.
También se recuerda que al utilizar la API de Virustotal, todas las URL y archivos que se analizan se almacenan PÚBLICAMENTE en la base de datos de Virustotal, así que tenga cuidado.
