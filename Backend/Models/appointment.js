const mongoose = require("mongoose");

const appointmentSchema = new mongoose.Schema({
  patientId: { type: mongoose.Schema.Types.ObjectId, ref: "Patient", required: true },
  doctorId: { type: mongoose.Schema.Types.ObjectId, ref: "Doctor", required: true },
  timeSlot: { type: String, required: true },
<<<<<<< HEAD
  status: { type: String, default: null } // "confirmed", "cancelled", or null
=======
  status: { type: String, default: "unconfirmed" }
>>>>>>> 4e42eac (Added ICP and Frontend)
});

const Appointment = mongoose.model("Appointment", appointmentSchema);

module.exports = { Appointment };