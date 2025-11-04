# Design Guide - Invoice Generator App

## 🎨 Visual Design Specifications

### Login Screen Layout

```
┌─────────────────────────────────────┐
│                                     │
│              60dp                   │
│                                     │
│         ┌──────────┐                │
│         │   LOGO   │  120x120dp     │
│         │    IG    │                │
│         └──────────┘                │
│                                     │
│              48dp                   │
│                                     │
│        Welcome Back                 │
│      Sign in to continue            │
│                                     │
│              40dp                   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 📧 Email / Mobile Number    │   │
│  └─────────────────────────────┘   │
│                                     │
│              16dp                   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │ 🔒 Password            👁   │   │
│  └─────────────────────────────┘   │
│                                     │
│                  Forgot Password?   │
│                                     │
│              32dp                   │
│                                     │
│  ┌─────────────────────────────┐   │
│  │        LOGIN                │   │
│  │    (Gradient Button)        │   │
│  └─────────────────────────────┘   │
│                                     │
│              24dp                   │
│                                     │
│  Don't have an account?             │
│  Create New Account                 │
│                                     │
└─────────────────────────────────────┘
```

### Dashboard Screen Layout

```
┌─────────────────────────────────────┐
│ ┌─────────────────────────────────┐ │
│ │ Invoice Generator    🔔  👤    │ │ ← Top App Bar (56dp)
│ └─────────────────────────────────┘ │
│                                     │
│  Quick Access                       │
│                                     │
│  ┌──────────┐  ┌──────────┐        │
│  │  📄      │  │  🚚      │        │
│  │ Create   │  │ Generate │        │
│  │ Invoice  │  │ E-Way    │        │
│  └──────────┘  └──────────┘        │
│                                     │
│  ┌──────────┐  ┌──────────┐        │
│  │  📝      │  │  💰      │        │
│  │ Create   │  │ Payment  │        │
│  │Quotation │  │ Receipt  │        │
│  └──────────┘  └──────────┘        │
│                                     │
│  ┌──────────┐  ┌──────────┐        │
│  │  👥      │  │  📦      │        │
│  │ Manage   │  │ Manage   │        │
│  │Customers │  │ Products │        │
│  └──────────┘  └──────────┘        │
│                                     │
│  ┌──────────┐  ┌──────────┐        │
│  │  📊      │  │  🏢      │        │
│  │ Reports  │  │ Business │        │
│  │          │  │ Profile  │        │
│  └──────────┘  └──────────┘        │
│                                     │
│ ┌─────────────────────────────────┐ │
│ │  🏠    📄    💳    ⚙️         │ │ ← Bottom Nav (80dp)
│ │ Home Invoices Payments Settings│ │
│ └─────────────────────────────────┘ │
└─────────────────────────────────────┘
```

## 🎨 Color System

### Primary Colors
```kotlin
PrimaryBlue       = #1976D2  ████████
PrimaryBlueLight  = #42A5F5  ████████
PrimaryBlueDark   = #0D47A1  ████████
SecondaryBlue     = #64B5F6  ████████
AccentBlue        = #2196F3  ████████
```

### Background Colors
```kotlin
BackgroundLight   = #F5F5F5  ████████
BackgroundWhite   = #FFFFFF  ████████
SurfaceWhite      = #FFFFFF  ████████
```

### Text Colors
```kotlin
TextPrimary       = #212121  ████████
TextSecondary     = #757575  ████████
TextHint          = #9E9E9E  ████████
TextWhite         = #FFFFFF  ████████
```

### Status Colors
```kotlin
ErrorRed          = #D32F2F  ████████
SuccessGreen      = #388E3C  ████████
```

## 📐 Spacing System

```
4dp   ▪
8dp   ▪▪
12dp  ▪▪▪
16dp  ▪▪▪▪
24dp  ▪▪▪▪▪▪
32dp  ▪▪▪▪▪▪▪▪
40dp  ▪▪▪▪▪▪▪▪▪▪
48dp  ▪▪▪▪▪▪▪▪▪▪▪▪
60dp  ▪▪▪▪▪▪▪▪▪▪▪▪▪▪▪
```

## 🔤 Typography Scale

```
Display Large    57sp  Bold      (Unused in current design)
Display Medium   45sp  Bold      (Unused in current design)
Display Small    36sp  Bold      (Unused in current design)

Headline Large   32sp  SemiBold  (Unused in current design)
Headline Medium  28sp  SemiBold  "Welcome Back"
Headline Small   24sp  SemiBold  "Quick Access"

Title Large      22sp  SemiBold  (Screen titles)
Title Medium     16sp  SemiBold  (Card titles)
Title Small      14sp  Medium    (Feature card labels)

Body Large       16sp  Normal    "Sign in to continue"
Body Medium      14sp  Normal    (General text)
Body Small       12sp  Normal    (Helper text)

Label Large      14sp  Medium    (Button text, links)
Label Medium     12sp  Medium    (Small buttons)
Label Small      11sp  Medium    (Tiny labels)
```

## 🎯 Component Specifications

### Gradient Button
- **Height**: 56dp
- **Border Radius**: 12dp
- **Elevation**: 4dp
- **Gradient**: Horizontal (PrimaryBlue → PrimaryBlueLight)
- **Text**: 16sp, SemiBold, White
- **Ripple**: White with 30% opacity

### Custom Text Field
- **Height**: Auto (min 56dp)
- **Border Radius**: 12dp
- **Border Width**: 1dp
- **Border Color**: 
  - Unfocused: BorderGray (#BDBDBD)
  - Focused: PrimaryBlue (#1976D2)
  - Error: ErrorRed (#D32F2F)
- **Text Size**: 16sp
- **Label Size**: 12sp (when floating)
- **Padding**: 16dp horizontal, 12dp vertical

### Feature Card
- **Aspect Ratio**: 1:1 (Square)
- **Border Radius**: 12dp
- **Elevation**: 3dp (default), 6dp (pressed)
- **Background**: White
- **Icon Size**: 48dp
- **Icon Color**: PrimaryBlue
- **Text Size**: 14sp, Medium
- **Padding**: 16dp
- **Spacing**: Icon to text = 12dp
- **Ripple**: PrimaryBlue with 20% opacity

### Top App Bar
- **Height**: 56dp
- **Background**: PrimaryBlue
- **Title**: 20sp, SemiBold, White
- **Icons**: 24dp, White
- **Elevation**: 4dp

### Bottom Navigation Bar
- **Height**: 80dp
- **Background**: White
- **Elevation**: 8dp
- **Icon Size**: 24dp
- **Label Size**: 12sp
- **Selected Color**: PrimaryBlue
- **Unselected Color**: TextSecondary
- **Indicator**: PrimaryBlueLight with 20% opacity

## 🎭 Animations & Interactions

### Button Press
- **Duration**: 150ms
- **Effect**: Ripple + Elevation change
- **Easing**: FastOutSlowIn

### Card Tap
- **Duration**: 200ms
- **Effect**: Ripple + Elevation increase (3dp → 6dp)
- **Easing**: FastOutSlowIn

### Screen Transitions
- **Duration**: 300ms
- **Effect**: Fade + Slide
- **Easing**: FastOutSlowIn

### Text Field Focus
- **Duration**: 200ms
- **Effect**: Border color change + Label float
- **Easing**: FastOutSlowIn

## 📱 Responsive Breakpoints

### Phone (Portrait)
- **Width**: 360dp - 480dp
- **Grid Columns**: 2
- **Card Spacing**: 16dp
- **Side Padding**: 24dp

### Phone (Landscape)
- **Width**: 480dp - 720dp
- **Grid Columns**: 3
- **Card Spacing**: 16dp
- **Side Padding**: 32dp

### Tablet (Portrait)
- **Width**: 720dp - 1024dp
- **Grid Columns**: 3
- **Card Spacing**: 24dp
- **Side Padding**: 48dp

### Tablet (Landscape)
- **Width**: 1024dp+
- **Grid Columns**: 4
- **Card Spacing**: 24dp
- **Side Padding**: 64dp

## ✨ Material Design 3 Features

### Elevation System
- **Level 0**: 0dp (Background)
- **Level 1**: 1dp (Cards at rest)
- **Level 2**: 3dp (Feature cards)
- **Level 3**: 6dp (Pressed cards)
- **Level 4**: 8dp (Bottom navigation)
- **Level 5**: 12dp (Dialogs)

### State Layers
- **Hover**: 8% opacity
- **Focus**: 12% opacity
- **Press**: 16% opacity
- **Drag**: 16% opacity

### Corner Radius
- **Small**: 8dp (Chips, small buttons)
- **Medium**: 12dp (Cards, buttons, text fields)
- **Large**: 16dp (Dialogs, sheets)
- **Extra Large**: 28dp (FABs)

## 🎨 Accessibility

### Color Contrast Ratios
- **Primary Text on Background**: 15.8:1 (AAA)
- **Secondary Text on Background**: 4.6:1 (AA)
- **White Text on Primary Blue**: 5.4:1 (AA)
- **Error Text**: 7.0:1 (AAA)

### Touch Targets
- **Minimum Size**: 48dp × 48dp
- **Recommended Size**: 56dp × 56dp (buttons)
- **Icon Buttons**: 48dp × 48dp

### Text Sizes
- **Minimum Body Text**: 14sp
- **Minimum Label Text**: 12sp
- **Recommended Body Text**: 16sp

---

**Design System Version**: 1.0  
**Last Updated**: 2024  
**Based on**: Material Design 3 Guidelines

