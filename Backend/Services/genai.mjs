import { GoogleGenerativeAI } from "@google/generative-ai";
import { configDotenv } from "dotenv";

configDotenv();

const genAI = new GoogleGenerativeAI(process.env.GEMINI_API_KEY);
const model = genAI.getGenerativeModel({ model: "gemini-2.0-flash" });

const generateResponse = async (prompt) => {
    const result = await model.generateContent(prompt);
    return result.response.text();
};

const translateToLanguage = async (text, lang) => {
    const prompt = `Translate the following text to ${lang}: ${text}. The translation should not be direct, but semantic. Only give the translation, no other text.`;
    const translation = await generateResponse(prompt);
    return translation;
}

export { generateResponse, translateToLanguage };