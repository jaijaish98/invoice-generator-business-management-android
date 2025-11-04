# Quick Start Guide

## 🚀 Getting Your App Running in 5 Minutes

### Step 1: Open in Android Studio

1. Launch **Android Studio Hedgehog (2023.1.1)** or later
2. Click **"Open"** or **"Open an Existing Project"**
3. Navigate to the project directory and select it
4. Click **"OK"**

### Step 2: Sync Gradle

Android Studio will automatically prompt you to sync Gradle files.

- If prompted, click **"Sync Now"**
- If not prompted, go to: **File → Sync Project with Gradle Files**
- Wait for the sync to complete (this may take a few minutes on first run)

### Step 3: Set Up an Emulator (if you don't have one)

1. Click the **Device Manager** icon in the toolbar
2. Click **"Create Device"**
3. Select a device (recommended: **Pixel 5** or **Pixel 6**)
4. Click **"Next"**
5. Select a system image (recommended: **API 34** - Android 14)
6. Click **"Next"** then **"Finish"**

### Step 4: Run the App

1. Select your device/emulator from the device dropdown
2. Click the **Run** button (green play icon) or press **Shift + F10**
3. Wait for the app to build and install
4. The app will launch automatically!

---

## 📱 What You'll See

### Login Screen
- A clean login interface with:
  - App logo (placeholder "IG")
  - Email/Mobile number field
  - Password field with visibility toggle
  - "Forgot Password?" link
  - Gradient "Login" button
  - "Create New Account" link

### Dashboard Screen (after login)
- Top app bar with notifications and profile icons
- "Quick Access" section with 8 feature cards:
  - Create Invoice
  - Generate E-Way Bill
  - Create Quotation
  - Payment Receipt
  - Manage Customers
  - Manage Products
  - Reports
  - Business Profile
- Bottom navigation with 4 tabs

---

## 🎯 Testing the App

### Login Screen Testing

**Valid Inputs:**
- Email: `test@example.com`
- Phone: `1234567890` or `+911234567890`
- Password: Any text with 6+ characters

**Try These Scenarios:**

1. **Empty Fields**
   - Leave fields empty and click Login
   - You'll see validation errors

2. **Invalid Email**
   - Enter: `notanemail`
   - Click Login
   - You'll see: "Please enter a valid email or mobile number"

3. **Short Password**
   - Enter: `12345` (less than 6 characters)
   - Click Login
   - You'll see: "Password must be at least 6 characters"

4. **Valid Login**
   - Email: `test@example.com`
   - Password: `password123`
   - Click Login
   - You'll navigate to the Dashboard

### Dashboard Testing

1. **Tap Feature Cards**
   - Tap any feature card
   - You'll navigate to a placeholder screen
   - Use the back button to return

2. **Bottom Navigation**
   - Tap each tab (Home, Invoices, Payments, Settings)
   - Content changes for each tab

3. **Top Bar Actions**
   - Tap the notification icon
   - Tap the profile icon

---

## 🛠️ Common Issues & Solutions

### Issue: Gradle Sync Failed

**Solution:**
1. Check your internet connection
2. Go to: **File → Invalidate Caches → Invalidate and Restart**
3. Try syncing again

### Issue: Emulator Won't Start

**Solution:**
1. Ensure virtualization is enabled in BIOS
2. Try creating a new emulator with a different API level
3. Restart Android Studio

### Issue: Build Errors

**Solution:**
1. Clean the project: **Build → Clean Project**
2. Rebuild: **Build → Rebuild Project**
3. Check that you have JDK 8 or higher installed

### Issue: App Crashes on Launch

**Solution:**
1. Check the Logcat for error messages
2. Ensure you're using API 24 or higher
3. Try uninstalling and reinstalling the app

---

## 🎨 Customization Quick Tips

### Change App Name
Edit: `app/src/main/res/values/strings.xml`
```xml
<string name="app_name">Your App Name</string>
```

### Change Primary Color
Edit: `app/src/main/java/com/invoicegenerator/ui/theme/Color.kt`
```kotlin
val PrimaryBlue = Color(0xFFYOURCOLOR)
```

### Add Your Logo
1. Place your logo in: `app/src/main/res/drawable/`
2. Edit: `app/src/main/java/com/invoicegenerator/ui/screens/LoginScreen.kt`
3. Replace the `AppLogo()` composable with:
```kotlin
Image(
    painter = painterResource(id = R.drawable.your_logo),
    contentDescription = "App Logo",
    modifier = Modifier.size(120.dp)
)
```

---

## 📚 Next Steps

### 1. Implement Authentication
- Add Firebase Authentication
- Or integrate with your backend API
- Update `LoginScreen.kt` to call your auth service

### 2. Build Feature Screens
- Start with the most important feature (e.g., Create Invoice)
- Create a new file in `ui/screens/`
- Add navigation in `NavGraph.kt`

### 3. Add Data Layer
- Implement Room Database for local storage
- Add Repository pattern
- Create ViewModels for state management

### 4. Integrate Backend
- Add Retrofit for API calls
- Create data models
- Implement network error handling

### 5. Add More Features
- PDF generation for invoices
- Camera integration for receipts
- Cloud sync
- Push notifications

---

## 📖 Useful Resources

### Official Documentation
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material Design 3](https://m3.material.io/)
- [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

### Tutorials
- [Compose Pathway](https://developer.android.com/courses/pathways/compose)
- [Material Design Guidelines](https://material.io/design)

### Community
- [Stack Overflow - Jetpack Compose](https://stackoverflow.com/questions/tagged/android-jetpack-compose)
- [Reddit - r/androiddev](https://reddit.com/r/androiddev)

---

## 🎉 Congratulations!

You now have a fully functional Android app with:
- ✅ Modern UI with Jetpack Compose
- ✅ Material Design 3
- ✅ Login screen with validation
- ✅ Dashboard with feature grid
- ✅ Navigation system
- ✅ Reusable components
- ✅ Professional color scheme

**Happy Coding! 🚀**

