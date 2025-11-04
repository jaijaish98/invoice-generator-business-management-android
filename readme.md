# Invoice Generator - Business Management App

A modern Android application built with **Jetpack Compose** for invoice generation and business management. The app features a clean, professional UI inspired by GimBooks with Material Design 3 principles.

## 🎨 Features

### Authentication
- **Login Screen** with email/mobile number and password authentication
- Input validation for email and phone numbers
- Password visibility toggle
- Forgot password functionality
- Create new account option

### Dashboard
- **Modern App Bar** with notifications and profile access
- **Quick Access Grid** with 8 feature cards:
  - Create Invoice
  - Generate E-Way Bill
  - Create Quotation
  - Payment Receipt
  - Manage Customers
  - Manage Products
  - Reports
  - Business Profile
- **Bottom Navigation** with 4 tabs (Home, Invoices, Payments, Settings)

## 🏗️ Architecture

### Project Structure
```
app/src/main/java/com/invoicegenerator/
├── ui/
│   ├── screens/
│   │   ├── LoginScreen.kt
│   │   └── DashboardScreen.kt
│   ├── components/
│   │   ├── GradientButton.kt
│   │   ├── CustomTextField.kt
│   │   └── FeatureCard.kt
│   ├── theme/
│   │   ├── Color.kt
│   │   ├── Type.kt
│   │   └── Theme.kt
│   └── navigation/
│       └── NavGraph.kt
├── utils/
│   └── ValidationUtils.kt
└── MainActivity.kt
```

## 🎨 Design System

### Color Palette (GimBooks Inspired)
- **Primary Blue**: `#1976D2`
- **Primary Blue Light**: `#42A5F5`
- **Primary Blue Dark**: `#0D47A1`
- **Secondary Blue**: `#64B5F6`
- **Background**: `#F5F5F5`
- **Text Primary**: `#212121`
- **Text Secondary**: `#757575`

### Typography
- **Headings**: 20-24sp, FontWeight 600-700
- **Body Text**: 14-16sp, FontWeight 400
- **Button Text**: 16sp, FontWeight 500-600

### Spacing
Consistent spacing using multiples of 8dp (8, 16, 24, 32, 40, 48, 60)

### Components
- **Rounded Corners**: 12dp border radius
- **Elevation**: 2-4dp for cards, 4dp for buttons
- **Ripple Effects**: Material ripple on all interactive elements

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Material Design**: Material 3
- **Navigation**: Navigation Compose
- **Architecture**: MVVM (ready for ViewModel integration)
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

## 📦 Dependencies

```kotlin
// Core
androidx.core:core-ktx:1.12.0
androidx.lifecycle:lifecycle-runtime-ktx:2.7.0
androidx.activity:activity-compose:1.8.2

// Compose
androidx.compose:compose-bom:2024.02.00
androidx.compose.ui:ui
androidx.compose.material3:material3
androidx.compose.material:material-icons-extended

// Navigation
androidx.navigation:navigation-compose:2.7.7

// ViewModel
androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 8 or higher
- Android SDK with API level 34

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd invoice-generator-business-management-flutter
```

2. Open the project in Android Studio

3. Sync Gradle files:
   - Click "Sync Now" when prompted
   - Or go to File → Sync Project with Gradle Files

4. Run the app:
   - Select a device/emulator
   - Click the Run button or press Shift+F10

## 📱 Screens

### 1. Login Screen
- App logo at the top
- Email/Mobile number input field
- Password input field with visibility toggle
- Forgot password link
- Gradient login button
- Create new account link

**Features:**
- Input validation
- Email and phone number format checking
- Password strength validation
- Loading states
- Error handling

### 2. Dashboard Screen
- Top app bar with app name, notifications, and profile
- Quick access feature grid (2 columns)
- Bottom navigation bar
- Tab-based content switching

**Features:**
- Smooth animations
- Ripple effects on cards
- Material 3 design
- Responsive layout

## 🎯 Navigation Flow

```
Login Screen
    ├── Dashboard Screen (on successful login)
    ├── Create Account Screen
    └── Forgot Password Screen

Dashboard Screen
    ├── Feature Screens (8 different features)
    ├── Profile Screen
    └── Tab Content (Home, Invoices, Payments, Settings)
```

## 🔧 Customization

### Changing Colors
Edit `app/src/main/java/com/invoicegenerator/ui/theme/Color.kt`:
```kotlin
val PrimaryBlue = Color(0xFF1976D2) // Change to your brand color
```

### Adding New Features
1. Create a new screen in `ui/screens/`
2. Add route in `ui/navigation/NavGraph.kt`
3. Add feature card in `DashboardScreen.kt`

### Modifying Typography
Edit `app/src/main/java/com/invoicegenerator/ui/theme/Type.kt`

## 📝 TODO / Future Enhancements

- [ ] Implement actual authentication logic
- [ ] Add backend integration
- [ ] Implement invoice creation functionality
- [ ] Add PDF generation for invoices
- [ ] Implement E-Way Bill generation
- [ ] Add customer and product management
- [ ] Implement reports and analytics
- [ ] Add data persistence (Room Database)
- [ ] Implement cloud sync
- [ ] Add multi-language support
- [ ] Implement dark theme

## 🧪 Testing

Run tests using:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📞 Support

For support, email support@invoicegenerator.com or create an issue in the repository.

---

**Built with ❤️ using Jetpack Compose**

