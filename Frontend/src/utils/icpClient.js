// Setup global polyfill
if (typeof window !== 'undefined') {
  window.global = window;
  // Basic process polyfill
  if (typeof process === 'undefined') {
    window.process = { env: { NODE_ENV: 'development' } };
  }
}

import { HttpAgent, Actor } from "@dfinity/agent";
import { idlFactory } from "/mnt/d/Hackathon/Sanjeevani/icp-sanjeevani/src/declarations/icp-sanjeevani-backend/icp-sanjeevani-backend.did.js";

// Create agent with fetch root key handling
const agent = new HttpAgent({ host: "http://localhost:4943" });

try {
  await agent.fetchRootKey();
} catch (err) {
  console.warn("Unable to fetch root key. Check if your local replica is running");
  console.error(err);
}

export const inventoryActor = Actor.createActor(idlFactory, {
  agent,
  canisterId: "dmalx-m4aaa-aaaaa-qaanq-cai",
});