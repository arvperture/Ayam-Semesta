# 🌌 LookUp — Stop Chasing Stars in the Dark. Find the Perfect Astro Spot.

> *"I spent 3 hours driving to a 'dark sky' spot, only to find heavy fog, a Bortle 6 glow from a nearby resort, and no idea what exposure time wouldn't trail the stars on my kit lens."*
> 
> **LookUp** was born out of real frustration from cold nights, ruined shots, and hours wasted scrolling light pollution maps. It’s an AI-powered night sky scout that tells you **where to go** tonight and **how to set your camera**, even if you have zero experience.

---

## ⚡ What Makes LookUp Different?

Instead of opening 5 different apps (Light Pollution Map, Weather, Stellarium, PhotoPills, Camera Calculators), **LookUp aggregates everything into one seamless workflow**:

1. **📍 5 Ideal Spots nearby:** Analyzed across **Today to Tomorrow (24–48h)** using real-time data from *Light Pollution Maps* and *MSN Weather* (clouds, fog & visibility).
2. **📷 Zero-Spec Camera Config:** Just input your gear name (e.g., `Sony A7 III` or `iPhone 15 Pro`). LookUp auto-calculates the exposure triangle (*500/NPF Rule, ISO, Aperture, FPS shutter rules*) specifically tailored for **each spot**.
3. **🎨 Creative Framing Guidance:** Custom Foreground (FG) & Background (BG) composition tips per spot.

---

## 🛠️ Architecture & Tech Stack

LookUp is still a web app, but it uses a small backend layer because some features need server-side processing that the browser alone cannot handle reliably: weather and light-pollution data aggregation, AI recommendations, and camera exposure calculations.

Built on a strict **MVC (Model-View-Controller)** pattern adhering to clean-code principles (DRY, SRP, descriptive naming):

```
   [ Client (Vanilla JS + Tailwind Glassmorphism) ]
                             │
                             ▼
   [ API Layer / Backend (Python FastAPI) ]
                   │                    │
                   ▼                    ▼
   [ AI Processing & Logic ]   [ Supabase DB ]
```

* **Frontend:** HTML5, Tailwind CSS (*Modern Minimalist Glassmorphism*), Vanilla ES6+ (*Standard Observer pattern — No Observable Objects*).
* **Backend:** Python FastAPI (*Weather Aggregator, AI Agent, Exposure Engine*).
* **Database:** Supabase (PostgreSQL).

> 📌 *For full architectural specs, flow diagrams, and database schemas, check out our [Product Requirement Document (PRD)](LookUp/LookUp_PRD_EN.md).*

---

## 🎨 Design System

Designed with a modern, dark-mode-first **Glassmorphism Aesthetic** tailored for night use:

* **Colors:** Deep Space Black (`#09090B`), Deep Midnight (`#0F172A`), Neon Purple (`#A855F7`), Muted Lavender (`#E9D5FF`).
* **UI Effects:** Frosted glass panels (`backdrop-filter: blur(16px)`), subtle neon borders, high-contrast cyan indicators.

---

## 🚀 Quick Start (Local Setup)

### 1. Clone & Set Environment Variables
```bash
git clone https://github.com/arvperture/Ayam-Semesta.git
cd Ayam-Semesta
```

### 2. Backend Setup (required for full features)
```bash
cd LookUp/Backend
python -m venv venv
source venv/bin/activate  # On Windows: venv\Scripts\activate
pip install -r requirements.txt
uvicorn main:app --reload
```

### 3. Frontend Setup
Serve the frontend folder with Live Server or any static server. If you only want to preview the UI, the frontend can run without the backend; the backend is needed for the full experience such as live data processing and AI recommendations.

---

## 🌌 Author's Note

LookUp was built by an astrographer, for astrographers. Whether you're chasing the core of the Milky Way with a $3,000 mirrorless rig or taking your first star photo on a smartphone, this tool is designed to make the night sky accessible to everyone.

**Clear skies & happy shooting! 🔭✨**
