# backend/app/services/ai_agent_orchestrator.py
from app.services.lightPolutionServices import fetch_light_pollution_index
from app.services.weatherServices import fetch_msn_weather_forecast

class AIAgentOrchestrator:
    async def run_pipeline(self, latitude: float, longitude: float, gear):
        # Step 3: Memanggil API analisis lingkungan
        light_data = await fetch_light_pollution_index(latitude, longitude)
        weather_data = await fetch_msn_weather_forecast(latitude, longitude)
        
        # Step 4 & 5: Lanjut pemrosesan skor spot & hitung setelan kamera...