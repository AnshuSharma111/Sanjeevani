const mongoose = require('mongoose');

const patientSchema = new mongoose.Schema({
    phoneno : { type: Number, required: [true, 'phoneno is a required field'], trim: true },
    appointmentno : { type: mongoose.Schema.Types.ObjectId, ref: "Appointment" , default: null},
    language: { type: String, default: 'en' }
});

const Patient = mongoose.model('Patient', patientSchema);

module.exports = { Patient };