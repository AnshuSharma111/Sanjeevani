require('dotenv').config();
const smsEvents = require('../eventBus');
<<<<<<< HEAD
const { Patient } = require('../Models/patient');
let genai;

(async () => {
    genai = (await import('./genai.mjs'));
})();

// Parse LLM responses
const parseResponse = (text) => {
    // Remove *** and ** from the text
    text = text.replace(/\*\*\*/g, "").replace(/\*\*/g, "");
    text = text.trim();
    return text;
}
=======
>>>>>>> 4e42eac (Added ICP and Frontend)

// POST method for sending message
const send = async (data) => {
    try {
<<<<<<< HEAD
        let { content, to } = data; // Extract data from the request
        if (!content || !to) {
            console.log("Invalid send Request!"); // Log error
            return;
        }

        const patient = await Patient.findOne({ phoneno: to }); // Find the patient
        const { translateToLanguage } = genai;
        content = await translateToLanguage(content, patient.language); // Translate the content to the patient's language
        content = parseResponse(content); // Parse the response

=======
        const { content, to } = data; // Extract data from the request
>>>>>>> 4e42eac (Added ICP and Frontend)
        const from = process.env.SEND_PHONE_NUMBER; // Extract the sender number from the environment variables
        console.log("Sending SMS to " + to + " with content: " + content); // Log the message

        const response = await fetch('https://api.httpsms.com/v1/messages/send', { // send SMS through httpSMS API
            method: "POST",
            headers: {
                "x-api-key": process.env.SMS_API_TOKEN,
<<<<<<< HEAD
                "Content-Type": "application/json"
=======
                "Content-Type": "application/json",
                "Accept": "application/json"
>>>>>>> 4e42eac (Added ICP and Frontend)
            },
            body: JSON.stringify({
                content: content,
                from: from,
                to: to
            })
        });

<<<<<<< HEAD
=======
        const res = await response.json(); // Parse the response

>>>>>>> 4e42eac (Added ICP and Frontend)
        if (!response.ok) {
            console.log("An error occured while sending SMS!"); // Log error
            console.log(response);
            return;
        }

        console.log("Sent SMS to " + to + " from " + from + " with content: " + content); // Log the message sent
    } catch (error) {
        console.log(`An error occured while sending SMS: ${error}`); // Log error
    }
}

const receive = async (req, res) => {
    const content = req.body.data.content; // Extract the content from the request
    const from = req.body.data.contact; // Extract the sender number from the request
    console.log("SMS received from " + from + " with content: " + content); // Log the received SMS

    if (!req.body) {
        console.log("Invalid receive Request!"); // Log error
        return res.status(400).json({ success: false, message: "No Body" });
    }

<<<<<<< HEAD
    // Check if user is registered or not
    const patient = await Patient.findOne({ phoneno: from });
    if (!patient) {
        // Identify the user's language
        const prompt = `Identify the user's language from the following message: ${content}\nAnswer in one word only.`;
        const { generateResponse } = genai;
        let NotTrimlanguage = await generateResponse(prompt); // Get the language using genai
        const language = NotTrimlanguage.trimEnd();

        console.log("User's language identified as: " + language); // Log the identified language
        // Add patient to the database if not registered
        const newPatient = new Patient({ phoneno: from, language: language });
        await newPatient.save(); // Save the new patient to the database
    }

    if (content[0] == "0") { // 0 is the code to book an appointment with a general physician
        console.log("Appointment booking request received"); // Log the request

        const len = content.length; // Get the length of the content
        if (len < 2) {
            smsEvents.emit("guide", { content, from});
            return;
        }
        const doctorType = content[1]; // Get the doctor type from the content
        console.log("Doctor type: " + doctorType); // Log the doctor type

        smsEvents.emit("book", { from, code: doctorType }); // Emit event to book an appointment
=======
    if (content == "0") { // 0 is the code to book an appointment
        console.log("Appointment booking request received"); // Log the request

        smsEvents.emit("register", { from }); // Emit event to book an appointment
>>>>>>> 4e42eac (Added ICP and Frontend)
        return res.status(200).json({ success: true, message: "Appointment booking request received" });
    }
    else if (content == "1") { // 1 is the code to confirm appointment
        console.log("Appointment confirmation request received"); // Log the request

        smsEvents.emit("confirm", { from }); // Emit event to confirm an appointment
        return res.status(200).json({ success: true, message: "Appointment confirmation request received" });
    }
    else if (content == "2") { // 2 is the code to cancel appointment
        console.log("Appointment cancellation request received"); // Log the request

        smsEvents.emit("cancel", { from }); // Emit event to cancel an appointment
        return res.status(200).json({ success: true, message: "Appointment cancellation request received" });
    }
    else {
        console.log("Unknown SMS received"); // Log the request

        smsEvents.emit("guide", { content, from });

        return res.status(200).json({ success: true, message: "Unknown SMS received" });
    }
}

module.exports = { send, receive };