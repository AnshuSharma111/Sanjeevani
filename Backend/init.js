const mongoose = require("mongoose");
const { Doctor } = require("./Models/doctor");
require("dotenv").config();

mongoose.connect(process.env.DB_URI);

<<<<<<< HEAD
const timeSlots = ["09:00-10:00", "10:00-11:00", "11:00-12:00", "13:00-14:00", "14:00-15:00"];

const doctors = [
  { name: "Dr. Raj", availableSlots: timeSlots, email: "amanna1041@gmail.com", speciality: "General Physician" },
  { name: "Dr. Paul", availableSlots: timeSlots, email: "contact@amitrajeetkonch.me", speciality: "General Physician" },
  { name: "Dr. Moo", availableSlots: timeSlots, email: "abc@gmail.com", speciality: "Cardiologist" },
  { name: "Dr. Tanaka", availableSlots: timeSlots, email: "tanaka321@gmail.com", speciality: "Dermatologist" }
=======
const doctors = [
  { name: "Dr. Raj", availableSlots: ["09:00-10:00", "10:00-11:00", "11:00-12:00"], email: "amanna1041@gmail.com" },
  { name: "Dr. Paul", availableSlots: ["09:00-10:00", "10:00-11:00", "11:00-12:00"], email: "contact@amitrajeetkonch.me" }
>>>>>>> 4e42eac (Added ICP and Frontend)
];

const seedDoctors = async () => {
  await Doctor.deleteMany({});
  await Doctor.insertMany(doctors);
<<<<<<< HEAD
  console.log("Doctors populated!");
=======
  console.log("✅ Doctors populated!");
>>>>>>> 4e42eac (Added ICP and Frontend)
  mongoose.connection.close();
};

seedDoctors();
