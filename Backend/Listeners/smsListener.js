const smsEvents = require("../eventBus"); // Import shared event emitter
const appointmentController = require("../Services/appointment"); // Import appointment controller
const smsController = require("../Services/sms"); // Import sms controller
const errorHandler = require("../Config/errors"); // Import error handler

// Import p-queue dynamically to support CommonJS
async function setupQueue() {
    const { default: PQueue } = await import('p-queue');
    const queue = new PQueue({ concurrency: 1 }); // Process events one by one

    // Wrap event handlers in queue.add() to ensure sequential processing
    smsEvents.on("register", (data) => queue.add(() => appointmentController.register(data)));
    smsEvents.on("book", (data) => queue.add(() => appointmentController.book(data)));
    smsEvents.on("confirm", (data) => queue.add(() => appointmentController.confirm(data)));
    smsEvents.on("cancel", (data) => queue.add(() => appointmentController.cancel(data)));
    smsEvents.on("guide", (data) => queue.add(() => appointmentController.guide(data)));
    smsEvents.on("sendSMS", (data) => queue.add(() => smsController.send(data)));

    smsEvents.on("error", (data) => queue.add(() => errorHandler.errorHandler(data)));

    console.log("Event queue initialized!");
}

// Initialize the queue
setupQueue().catch(console.error);