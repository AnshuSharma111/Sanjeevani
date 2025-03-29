const { Doctor } = require("../models/doctor");

const applyLeave = async (req, res) => {
    try {
        const { doctorId, leaveDate } = req.body;

        // Validate input
        if (!doctorId || !leaveDate) {
            console.log("Doctor ID and leave date are required");
            return res.status(400).json({ message: "Doctor ID and leave date are required" });
        }

        // Fetch doctor details
        const doctor = await Doctor.findById(doctorId);
        if (!doctor) {
            console.log("Doctor not found");
            return res.status(404).json({ message: "Doctor not found" });
        }

        // Check if leave date is already applied
        const leaveExists = doctor.leaves.some(leave => leave.toISOString().split("T")[0] === leaveDate.split("T")[0]);
        if (leaveExists) {
            console.log("Leave already applied for this date");
            return res.status(400).json({ message: "Leave already applied for this date" });
        }

        // Check if leave date is in the past
        const currentDate = new Date();
        const leaveDateObj = new Date(leaveDate);
        if (leaveDateObj < currentDate) {
            console.log("Leave date cannot be in the past");
            return res.status(400).json({ message: "Leave date cannot be in the past" });
        }

        // Add leave date to doctor's leaves array
        doctor.leaves.push(leaveDate);
        await doctor.save();
        console.log("Leave applied successfully");
        return res.status(200).json({ message: "Leave applied successfully" });
    }
    catch (error) {
        console.log("Some error occurred while applying leave", error);
        return res.status(500).json({ message: "Internal server error" });
    }
}

module.exports = { applyLeave };