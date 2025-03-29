const mongoose = require("mongoose");

const doctorSchema = new mongoose.Schema({
  name: { type: String, required: true },
  availableSlots: [{ type: String }],
  email: { type: String, required: true },
  speciality: { type: String, required: true },
  leaves: { type: [Date], default: [] }
});

const Doctor = mongoose.models.Doctor || mongoose.model("Doctor", doctorSchema);

module.exports = { Doctor };