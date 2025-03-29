const mongoose = require("mongoose");

const doctorSchema = new mongoose.Schema({
  name: { type: String, required: true },
  availableSlots: [{ type: String }], // Example: ["09:00-10:00", "10:00-11:00"]
<<<<<<< HEAD
  email: { type: String, required: true },
  speciality: { type: String, required: true }
=======
  email: { type: String, required: true }
>>>>>>> 4e42eac (Added ICP and Frontend)
});

const Doctor = mongoose.model("Doctor", doctorSchema);

module.exports = { Doctor };