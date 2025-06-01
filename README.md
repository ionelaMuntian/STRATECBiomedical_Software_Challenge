# 🚀 STRATEC Biomedical – 2025 Software Challenge

**Implemented by:** Mariana-Ionela Muntian  
**📺 Video Demo:** [Watch the demo](https://drive.google.com/drive/folders/1S7ttVkd4Mug45trtaZwTTki46qhwfCEH)

---

## Overview

This project is a complete solution to the **2025 Internship Software Challenge** by STRATEC Biomedical. It simulates planetary physics and rocket travel in a simplified solar system across seven progressive stages.

---

## Features by Stage

### Stage 1 – Perfect Spheres in a Vacuum
- Reads `Planetary_Data.txt` and computes **escape velocity** for each planet.
- Uses the formula:  
  \[
  v = \sqrt{\frac{2GM}{r}}
  \]
- All units are in SI; radii are converted from kilometers to meters.

### Stage 2 – Up, Up and Away!
- Uses rocket engine data from `Rocket_Data.txt`.
- Calculates:
  - Time to reach escape velocity
  - Distance traveled during acceleration

### Stage 3 – Interplanetary Travel (Simplified)
- Computes straight-line journeys between aligned planets.
- Calculates:
  - Acceleration & deceleration time
  - Cruise time
  - Total trip duration in days/hours/minutes/seconds

### Stage 4 – Planetary Orbits
- Simulates **angular positions** of planets after a given number of days.
- Assumes circular, coplanar, clockwise orbits from `Solar_System_Data.txt`.

### Stage 5 – Optimal Transfer Window (Static System)
- Finds optimal launch windows between any two planets.
- Ensures:
  - Minimal distance
  - No collision with other planetary orbits

### Stage 6 – Optimal Transfer Window (Dynamic System)
- Adds realism by simulating **planetary motion during flight**.
- Calculates safe and efficient transfer windows accordingly.

### Stage 7 – Graphical User Interface
- Built with **JavaFX**
- Allows:
  - Selecting planets
  - Running simulations
  - Viewing travel and orbital results interactively

---
