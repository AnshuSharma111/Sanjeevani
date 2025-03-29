const mongoose = require('mongoose');

const patientSchema = new mongoose.Schema({
    phoneno : { type: Number, required: [true, 'phoneno is a required field'], trim: true },
<<<<<<< HEAD
    appointmentno : { type: mongoose.Schema.Types.ObjectId, ref: "Appointment" , default: null},
    language: { type: String, default: 'en' }
=======
    appointmentno : { type: mongoose.Schema.Types.ObjectId, ref: "Appointment" },
>>>>>>> 4e42eac (Added ICP and Frontend)
});

const Patient = mongoose.model('Patient', patientSchema);

module.exports = { Patient };