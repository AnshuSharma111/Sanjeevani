const Patient = require('../Models/patient').Patient; // Import patient model
const Doctor = require('../Models/doctor').Doctor; // Import doctor model
const Appointment = require('../Models/appointment').Appointment; // Import appointment model
const smsEvents = require('../eventBus'); // Import event bus
<<<<<<< HEAD

let genai;

(async () => {
    genai = await import('./genai.mjs');
})();

const specialities = {
    "a" : "General Physician",
    "b" : "Cardiologist",
    "c" : "Dermatologist"
}
=======
const { generateResponse } = require("./genai.mjs"); // Import function to prompt model
require("./genai.mjs"); // Import Model
>>>>>>> 4e42eac (Added ICP and Frontend)

const register = async (data) => {
    try {
        const { from } = data; // Phone number SMS received from and it's content

        // Check if user already exists in database
        const user = await Patient.findOne({ phoneno: from });
        if (user) {
            console.log(`User ${from} already has an impending appointment`);
            smsEvents.emit("error", { errorCode: 0, user: from }); // Emit error if anything goes wrong
<<<<<<< HEAD
=======

>>>>>>> 4e42eac (Added ICP and Frontend)
            return;
        }

        console.log("Registering to database..."); // log

        const newPatient = new Patient({ phoneno: from }); // Create a new patient and save it to the databse with status unconfirmed
        await newPatient.save();

        console.log("Patient registered successfully"); // log

<<<<<<< HEAD
        smsEvents.emit("book", { from: from }); // Emit patient Registered event
=======
        smsEvents.emit("book", { from: from }); // Emit patientRegistered event
>>>>>>> 4e42eac (Added ICP and Frontend)
    } catch (error) {
        console.log(`An error occurred: ${error}`); // log error
        smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error event
    }
};

const book = async (data) => {
    try {
<<<<<<< HEAD
        const { from, code } = data;
=======
        const { from } = data;
>>>>>>> 4e42eac (Added ICP and Frontend)

        // Check whether the patient has been regsitered or not
        const user = await Patient.findOne({ phoneno: from });
        if (!user) {
            console.log(`User ${from} not registered.`);
            smsEvents.emit("error", { errorCode: 1, user: from }); // Emit error if anything goes wrong
            return;
        }

<<<<<<< HEAD
        const reqdSpeciality = specialities[code]; // Get the required speciality from the code
        if (!reqdSpeciality) {
            console.log(`Invalid doctor type code: ${code}`); // log error
            smsEvents.emit("error", { errorCode: 4, user: from }); // Emit error if anything goes wrong
            return;
        }
        console.log(`Finding Doctor of speciality ${reqdSpeciality}...`);

        // Get all doctors of speciality
        const doctors = await Doctor.find({ speciality: reqdSpeciality });
        // Get the doctor with the earliest available empty slot
        let doctor = doctors[0];
        for (let i = 0; i < doctors.length; i++) {
            if (doctors[i].availableSlots.length > 0 && doctors[i].availableSlots[0].split("-")[0] < doctor.availableSlots[0].split("-")[0]) {
                doctor = doctors[i];
            }
        }
=======
        console.log('Finding Doctor...');

        // Get the doctor with the earliest available empty slot
        const doctor = await Doctor.findOne({ availableSlots: { $ne: [] } }).sort({ "availableSlots.0": 1 });
>>>>>>> 4e42eac (Added ICP and Frontend)
    
        if (!doctor) {
            console.log("No available doctors");
            smsEvents.emit("error", { errorCode: 3, user: from }); // Emit error if anything goes wrong
            return;
        }
<<<<<<< HEAD

=======
      
>>>>>>> 4e42eac (Added ICP and Frontend)
        // Assign the first available slot
        console.log('Found Doctor, assigning slot...');
        const assignedSlot = doctor.availableSlots.shift();
        // Update doctor in DB
        await doctor.save();

        // Save the appointment in DB
        const newAppointment = await Appointment.create({
            patientId: user._id,
            doctorId: doctor._id,
            timeSlot: assignedSlot
        });
        // Assign appointment id to patient
        user.appointmentno = newAppointment._id;
<<<<<<< HEAD
        user.status = "pending";
=======
>>>>>>> 4e42eac (Added ICP and Frontend)
        await user.save();

        console.log(`Appointment booked for ${from} with ${doctor.name} at ${assignedSlot}`);

<<<<<<< HEAD
        const content = "Hello, you have an appointment with " + doctor.name + " at " + assignedSlot + ". Please reply with 1 to confirm or 2 to cancel.";
    
=======
        const content = "Hello, you have an appointment with " + doctor.name + " at " + assignedSlot + ". Please reply with 1 to confirm or 2 to cancel."; // Create the message content
>>>>>>> 4e42eac (Added ICP and Frontend)
        // Emit event to send confirmation SMS
        smsEvents.emit("sendSMS", { content, to: from });
    }
    catch (error) {
        console.log(`An error occurred: ${error}`); // log error
        smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error
    }
};

const confirm = async (data) => {
    try {
        const { from } = data; // Extract Data

        // Find the user and check if they have an appointment
        const user = await Patient.findOne({ phoneno: from });

        if (!user) { // Return if user is not registered
            console.log(`User ${from} not registered.`);
            smsEvents.emit("error", { errorCode: 1, user: from }); // Emit error if anything goes wrong
            return;
        }

        // Get the associated appointment with the patient
        const appointment = await Appointment.findOne({ patientId: user._id });
        if (appointment.status == "confirmed") { // Return if user already has confirmed the appointment
            console.log(`User ${from} already has an appointment.`);
            smsEvents.emit("error", { errorCode: 0, user: from }); // Emit error if anything goes wrong
            return;
        }

        // Find the appointment and update the status to confirmed
        console.log("Updating appointment status...");

        appointment.status = "confirmed";
        await appointment.save();

        // Get associated doctor and time slot
        const doctor = await Doctor.findOne({ _id: appointment.doctorId });
        const timeSlot = appointment.timeSlot;

        // Send SMS to the patient that appointment has been confirmed
        const content = "Hello, your appointment with " + doctor.name + " at " + timeSlot + " has been confirmed."; // Create the message content
        // Send the confirmation SMS
        smsEvents.emit("sendSMS", { content, to: from });
    }
    catch (err) {
        console.log(`An error occurred: ${err}`); // log error
        smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error
    }
};

const cancel = async (data) => {
    try {
        const { from } = data; // Extract Data

        // Find Appointment linked to the phoneno
        const patient = await Patient.findOne({ phoneno: from });
        if (!patient) { // Return if user is not registered
            console.log(`User ${from} not registered.`);
            smsEvents.emit("error", { errorCode: 1, user: from }); // Emit error if anything goes wrong
            return;
        }
        const appointment = await Appointment.findOne({ patientId: patient._id });
        if (!appointment) { // Return if user is not registered
            console.log(`No appointment for user ${from}.`);
            smsEvents.emit("error", { errorCode: 2, user: from }); // Emit error if anything goes wrong
            return;
        }

        // Get the associated appointed time slot and re-add it to the associated doctor's schedule
        console.log("Getting booked time slot...");

        const doctor = await Doctor.findOne({ _id: appointment.doctorId });
<<<<<<< HEAD
        // Sort slots in ascending order
        const convertTo24HourFormat = (slot) => {
            const [start, end] = slot.split("-");
            
            // Convert start time
            let [startHour, startMin] = start.split(":").map(Number);
            if (startHour < 9) startHour += 12; // Convert AM/PM properly
            
            return `${startHour.toString().padStart(2, "0")}:${startMin.toString().padStart(2, "0")}`;
        };

        // Add the canceled slot
        doctor.availableSlots.push(appointment.timeSlot);
        
        // Sort the slots correctly
        doctor.availableSlots.sort((a, b) => {
            return convertTo24HourFormat(a).localeCompare(convertTo24HourFormat(b));
        });

=======
        doctor.availableSlots.push(appointment.timeSlot);
>>>>>>> 4e42eac (Added ICP and Frontend)
        await doctor.save();

        console.log("Added time slot back to doctor's schedule...");

        // Delete the appointment and user
<<<<<<< HEAD
        console.log("Deleting appointment and resetting user...");
=======
        console.log("Deleting appointment and user...");
>>>>>>> 4e42eac (Added ICP and Frontend)

        let response = await Appointment.deleteOne({ patientId: patient._id });
        if (response.deletedCount == 0) { // Check if the appointment was deleted
            console.log("Could not delete appointment.");
            smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error if anything goes wrong
            return;
        }
        console.log("Deleted appointment successfully.");
<<<<<<< HEAD

        patient.appointmentno = null; // Reset appointment number
        console.log("Reset user...");
=======
        response = await Patient.deleteOne({ _id: patient._id });
        if (response.deletedCount == 0) { // Check if the appointment was deleted
            console.log("Could not delete patient.");
            smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error if anything goes wrong
            return;
        }
        console.log("Deleted patient successfully.");

>>>>>>> 4e42eac (Added ICP and Frontend)
        // Send SMS to the patient that appointment has been cancelled
        const content = "Hello, your appointment has been cancelled."; // Create the message content
        smsEvents.emit("sendSMS", { content, to: from }); // Send the confirmation SMS
    }
    catch (error) {
        console.log(`An error occurred: ${error}`); // log error
        smsEvents.emit("error", { errorCode: -1, user: from }); // Emit error
    }
}

<<<<<<< HEAD
=======
// Handle unknown SMS
>>>>>>> 4e42eac (Added ICP and Frontend)
const guide = async (data) => {
    try {
        const { content, from } = data;

        console.log("Handling unknown SMS...");

        if (!content || !from) {
            console.log("No content or from!");
<<<<<<< HEAD
            smsEvents.emit("error", { errorCode: -1, user: from });
            return;
=======
            smsEvents.emit("error", { errorCode: -1, user: from }); // SET LATER
>>>>>>> 4e42eac (Added ICP and Frontend)
        }

        console.log("Generating response to unknown SMS...");

<<<<<<< HEAD
        if (!genai) {
            console.log("genai module not loaded yet!");
            smsEvents.emit("error", { errorCode: -1, user: from });
            return;
        }

        const { generateResponse } = genai; // Extract the function
        const prompt = `
            You are a chatbot that provides guidance to users regarding booking an appointment in a hospital.
            Your task is to guide the user through the process of booking an appointment.
            The user has the following options:
            0, followed by either a, b, or c to back in an appointment with a doctor of the specified speciality.
            0a for General Physician
            0b for Cardiologist
            0c for Dermatologist
            1 to confirm an existing appointment
            2 to cancel an existing appointment
            The user sent: ${content}. Guide them accordingly.
            Under no circumstance should you divert from the topic of booking an appointment.
        `;
=======
        const prompt = "You are a chatbot that provides guidance to users trying to use an application booking system. The user needs to press 0 to book appointment, 1 to confirm appointment and 2 to cancel it. The user has sent content " + content + ". Guide them accordingly.";
>>>>>>> 4e42eac (Added ICP and Frontend)

        const response = await generateResponse(prompt);

        if (!response) {
            console.log("Could not generate response!");
<<<<<<< HEAD
            smsEvents.emit("error", { errorCode: -1, user: from });
            return;
        }

        console.log("Generated response: " + response);
        smsEvents.emit("sendSMS", { content: response, to: from });
    } catch (error) {
        console.log(`An error occurred: ${error}`);
        smsEvents.emit("error", { errorCode: -1, user: from });
    }
};

=======
            smsEvents.emit("error", { errorCode: -1, user: from }); // SET LATER
        }

        console.log("Generated response " + response);

        smsEvents.emit("sendSMS", { content: response, to: from });
    } catch (error) {
        console.log(`An error occurred: ${error}`); // log error
        smsEvents.emit("error", { errorCode: -1, user: from }); // SET LATER
    }
}
>>>>>>> 4e42eac (Added ICP and Frontend)
module.exports = { register, book, confirm, cancel, guide }; // Export register and book functions