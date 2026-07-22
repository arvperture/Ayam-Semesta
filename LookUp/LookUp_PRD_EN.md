# Product Requirement Document (PRD): LookUp

**Version:** 1.0  
**Date:** July 22, 2026  
**Status:** Approved
**Author:** LookUp's Dev

---

## 1. Product Overview

### 1.1 Product Vision
**LookUp** is an intelligent web-based platform designed to assist astrophotographers—ranging from zero-experience beginners to seasoned professionals—in discovering the absolute best night-time shooting spots. LookUp fuses real-time weather metrics, light pollution data, cloud/fog visibility, and camera/smartphone gear specifications to deliver precise spot recommendations and tailored camera settings.

### 1.2 Core Objectives
* **Automated Spot Discovery:** Identify the top 5 astrophotography spots around a requested target area based on minimal light pollution and optimal meteorological conditions (today through tomorrow).
* **Automated Exposure & Composition Engine:** Provide location-specific exposure triangle settings (*ISO, Aperture, Shutter Speed*) and foreground/background composition recommendations customized for each spot and user gear.
* **Beginner Accessibility:** Empower non-technical users to capture stunning night sky images with zero prior camera knowledge by simply submitting their gear name.

---

## 2. Target Audience & Personas

1. **Casual / Zero-Experience Astrophotographers**
   * **Profile:** Users with flagship/mid-range smartphones or entry-level cameras who want to capture the Milky Way without understanding technical concepts like ISO or shutter speed rules.
   * **Needs:** Plug-and-play camera settings, clear step-by-step guidance, and safe, easily accessible location recommendations.
2. **Intermediate & Professional Astrophotographers**
   * **Profile:** Experienced shooters carrying dedicated DSLR/Mirrorless setups with wide-aperture lenses.
   * **Needs:** Granular weather data, Bortle scale ratings, precise cloud cover analysis, and creative foreground composition ideas.

---

## 3. System Architecture & Tech Stack

The platform follows a clean **MVC (Model-View-Controller)** pattern separating presentation, business orchestration, and data persistence.

### 3.1 Technology Stack
* **Frontend (View):**
  * HTML5
  * Tailwind CSS (Custom Minimalist Modern Glassmorphism Theme)
  * JavaScript (Vanilla ES6+ using Standard Reactive Observable Pattern - *No Observable Objects*)
* **Backend (Controller & Service Layer):**
  * Node.js / JavaScript (API Gateway & Request Router)
  * Python FastAPI (Core Intelligence Engine, AI Agent Pipelines, Weather Data Scraper/Aggregator, Math Calculations)
* **Database (Model):**
  * Supabase (PostgreSQL, Realtime Capabilities, Row Level Security)

---

## 4. Software Design Rules & Standards

All developers must adhere strictly to the following programming principles:

1. **Naming Conventions:**
   * **CamelCase (`camelCase`):** Used for variables and functions in JavaScript/Frontend (e.g., `getWeatherForecast`, `selectedGearName`).
   * **Snake Case (`snake_case`):** Used for variables and functions in Python FastAPI and Supabase database columns (e.g., `calculate_exposure_triangle`, `light_pollution_score`).
   * **PascalCase (`PascalCase`):** Used for Classes and Type Interfaces in Python/JS (e.g., `LocationController`, `GearSpecification`).
   * **Descriptive Naming:** Single-letter or vague variable names like `$x`, `$data`, `temp`, or `val` are strictly forbidden.

2. **DRY (Don't Repeat Yourself):**
   * Exposure calculations, date formatting, and API client routines must be centralized in utility modules.
   * Glassmorphism styles must be encapsulated via Tailwind `@apply` rules or shared CSS modules.

3. **Single Responsibility Principle (SRP - Function Level):**
   * Each function or method MUST execute exactly one logical task.
   * **Example:** `get_weather_data()` fetches weather data. `calculate_cloud_cover()` processes cloud density. `save_location()` handles database writes. Merging location retrieval and weather calculations into one function is prohibited.

4. **Reactive State Management (Observable Pattern):**
   * **Strict Constraint:** Do NOT use `Observable Object`. Use the standard **Observable** pattern (custom Pub/Sub or lightweight RxJS Subject pattern) for UI reactive updates.

---

## 5. End-to-End Backend Flow

The system executes a 10-step automated pipeline:

```
[User Input] -> [Database Input] -> [AI Agent Trigger] -> [Data Aggregation & Spot Analysis]
     ^                                                                   |
     |                                                                   v
[UI Render] <- [Fetch Output] <- [Insight DB Save] <- [AI Gear Engine] <- [5 Best Spots]
```

### Detailed Sequential Steps (1 - 10):
1. **Frontend Input:** User submits required parameters:
   * Target Location (e.g., "Malang")
   * Country (e.g., "Indonesia")
   * Gear Name (e.g., "Sony A7 III" or "iPhone 15 Pro") - *Gear name only, no specs required from user*.
   * Shooting Objective (*Static Photo*, *Lightrails*, *Video [FPS]*, or *Timelapse*).
2. **Database Persistence (Initial):** Backend receives payload and logs the record into Supabase `user_requests` table.
3. **AI Agent Dispatch:** AI Agent picks up the request using only target location and country.
4. **AI API Location Analysis:** AI Agent evaluates the target area across a **Today-to-Tomorrow timeframe (24–48 hours)** using web data sources:
   * Light Pollution Levels (from *Light Pollution Map* / Bortle Scale).
   * Weather Conditions & Temperature (from *MSN Weather*).
   * Cloud & Fog Visibility (from *MSN Weather*).
5. **AI Output Spot Selection:** AI Engine filters and produces the **5 Best Location Spots** with minimal light interference and maximum sky clarity.
6. **Database Persistence & Secondary AI Call:** Backend saves the 5 spots into `recommended_spots` table and passes them back to the AI Gear Analysis Agent along with user gear details.
7. **AI Gear & Location Calibration:** AI Agent computes optimal camera settings specifically tailored for **each of the 5 locations** (accounting for local brightness, cloud density, and gear optics).
8. **Insight Storage:** Complete result package (5 spots + composition suggestions + individual exposure triangles) is saved into `astro_insights` table in Supabase.
9. **Backend Response Preparation:** Backend retrieves the populated records from Supabase and serializes a clean REST JSON payload.
10. **Frontend Presentation:** Frontend receives the payload and renders the 5 spots via an interactive Glassmorphism UI.

---

## 6. Detailed AI Output Specifications & Formulas

Every recommended spot among the 5 locations includes:

1. **Spot Recommendation:** Name, geo-coordinates, Bortle Class (1–9), and clear sky probability percentage.
2. **Composition Guidance:**
   * **Foreground (FG):** Recommended earthly elements (tree silhouettes, rock formations, water reflections, architectural silhouettes).
   * **Background (BG):** Core Milky Way position, dominant constellations, or celestial polar alignment.
3. **Individual Exposure Triangle Rules:**
   * **Static Sky Photo:** Calculated using the *500 Rule* or *NPF Rule* (`Max Shutter Speed = 500 / (Focal Length * Crop Factor)`).
   * **Light Trails:** Long shutter exposure (15s–30s / Bulb mode), low ISO (100–400), closed aperture (f/8–f/11).
   * **Video (FPS-based):** Adheres to the 180-degree shutter rule (`Shutter Speed = 1 / (2 * FPS)`), ISO scaled to sensor noise threshold.
   * **Timelapse:** Intervalometer settings (e.g., capture every 5 seconds, 15s exposure, ISO 1600–3200, 2-hour duration).

---

## 7. Database Schema (Supabase PostgreSQL)

```sql
-- User Requests Table
CREATE TABLE user_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    created_at TIMESTAMP WITH TIME ZONE DEFAULT timezone('utc'::text, now()) NOT NULL,
    target_location VARCHAR(255) NOT NULL,
    country VARCHAR(100) NOT NULL,
    gear_name VARCHAR(255) NOT NULL,
    shooting_objective VARCHAR(50) NOT NULL -- 'static', 'lightrail', 'video', 'timelapse'
);

-- Recommended Spots Table (5 Spots per Request)
CREATE TABLE recommended_spots (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    request_id UUID REFERENCES user_requests(id) ON DELETE CASCADE,
    spot_name VARCHAR(255) NOT NULL,
    latitude NUMERIC(10, 8),
    longitude NUMERIC(11, 8),
    bortle_scale INT NOT NULL,
    weather_summary VARCHAR(255),
    cloud_cover_percentage INT,
    visibility_km NUMERIC(5, 2)
);

-- Astro Insights Table (Camera & Composition per Spot)
CREATE TABLE astro_insights (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    spot_id UUID REFERENCES recommended_spots(id) ON DELETE CASCADE,
    foreground_recommendation TEXT NOT NULL,
    background_recommendation TEXT NOT NULL,
    iso_setting INT NOT NULL,
    aperture_setting VARCHAR(20) NOT NULL,
    shutter_speed_setting VARCHAR(20) NOT NULL,
    focus_mode VARCHAR(50) DEFAULT 'Manual Focus (Infinity)',
    additional_notes TEXT
);
```

---

## 8. UI/UX & Design System Guidelines

* **Design Aesthetic:** Minimalist Modern Glassmorphism (Frosted Glass Cards, Subtle Borders, High Contrast Accent Lighting).
* **Color Palette:**
  * **Primary / Accent Purple:** Neon Purple (`#A855F7`), Deep Violet (`#6B21A8`)
  * **Background:** Deep Space Black (`#09090B`), Midnight Slate (`#0F172A`)
  * **Glass Panels:** `rgba(255, 255, 255, 0.05)` with `backdrop-filter: blur(16px)` and border `rgba(255, 255, 255, 0.1)`
  * **Typography / Highlights:** Pure White (`#FFFFFF`), Lavender (`#E9D5FF`), Electric Cyan (`#06B6D4`) for active status indicators.

---

## 9. Constraints & Anti-Patterns (Don'ts)
* **DO NOT** use `Observable Object` for frontend reactive state. Use standard event-based Observer pattern instead.
* **DO NOT** ask users to enter detailed sensor or lens specifications manually. The system auto-resolves specs from the provided `gear_name`.
