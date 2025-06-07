<h1>
  <img src="https://github.com/morish276/ContactManagerApp/blob/main/app/src/main/res/drawable/Copilot_20250605_133704.png" alt="ManageMate Logo" width="40"/>
  ManageMate — A Contact Manager App
</h1>

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue?logo=kotlin&style=flat-square)](https://kotlinlang.org/)
[![Android](https://img.shields.io/badge/platform-Android-blue?logo=android&style=flat-square)](https://developer.android.com/)
[![License: MIT](https://img.shields.io/badge/license-MIT-yellow.svg?style=flat-square)](https://github.com/morish276/ContactManagerApp/blob/main/LICENSE)
[![Issues](https://img.shields.io/github/issues/morish276/ContactManagerApp?style=flat-square)](https://github.com/morish276/ContactManagerApp/issues)

ManageMate is a simple yet functional **Contact Management Android app** built using modern Android architecture components. It allows users to **add, update, and delete contact entries**, backed by **Room Database** and **LiveData** for real-time UI updates.

✅ **All code is well-commented with step-by-step explanations** to help beginners learn easily.

---

## 📱 Features

- 🔹 Add new contacts with name and email
- 🔄 Update and delete existing contacts
- 🗃️ Dynamic RecyclerView to display contact list
- ♻️ Two-way Data Binding with ViewModel
- 💾 Persistent storage with Room Database
- 📦 MVVM Architecture
- 🌙 Forced Light Mode (ignores system theme)

---

## 📸 Demo

https://github.com/user-attachments/assets/8413c95f-c6fc-4f3e-accb-47992fc7bb31

---

## 🧱 Tech Stack

| Layer         | Technology                        |
|---------------|------------------------------------|
| Language       | Kotlin                            |
| UI             | XML, ConstraintLayout             |
| Architecture   | MVVM                              |
| Storage        | Room Database + DAO + Repository  |
| Lifecycle Mgmt | ViewModel + ViewModelFactory      |
| UI Sync        | LiveData + DataBinding            |
| List View      | RecyclerView with custom Adapter  |

---

## 🏗️ Project Structure

![32f73816-18ce-49f4-aad4-9a88402a0a05](https://github.com/user-attachments/assets/5b89b9f2-16a5-490c-9fcf-e0ac12812dbc)

---

## 💡 App Architecture (MVVM + Room)

```
UI (MainActivity, activity_main.xml)
│
▼
ViewModel (ContactViewModel)
│
▼
Repository (ContactRepository)
│
▼
Room Database (DAO + Contact Entity)
```

---

## 🔄 Flow of Data

1. User enters Name and Email → Clicks Save

2. ViewModel (saveOrUpdate) calls Repository

3. Repository calls DAO to insert data into Room DB

4. LiveData<List<Contact>> observes Room changes

5. RecyclerView updates automatically

6. Clicking on item sends selected contact to ViewModel for editing

---

## 🧠 Learnings

This project helped me understand and implement:

- Clean MVVM pattern
- Room persistence with DAO and Repository
- LiveData observation and automatic UI updates
- Data binding to simplify ViewModel interaction
- RecyclerView adapter customization
- UI consistency with forced light mode

---

## ⚙️ How to Run

1. Clone this repo  
   `git clone https://github.com/morish276/ContactManagerApp.git`
2. Open in Android Studio
3. Click **Run ▶** or use emulator/device

---

## 🙌 Feedback or Contributions

Have suggestions or found a bug?  
Feel free to [open an issue](https://github.com/morish276/ContactManagerApp/issues) or submit a pull request.

⭐ If you found this project helpful, consider giving it a star to show your support!

---

Thanks for checking out **ContactManagerApp**!  
Happy coding! 💻🚀
