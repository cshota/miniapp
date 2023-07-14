// import { createSSRApp } from "vue";
// import App from "./App.vue";

// export function createApp() {
//   const app = createSSRApp(App);
//   return {
//     app,
//   };
// }

import { createSSRApp } from 'vue';
import App from './App.vue';

// Firebase
import { initializeApp } from 'firebase/app';
import { getFirestore } from 'firebase/firestore';
// Optional: Analytics
import { getAnalytics } from 'firebase/analytics';

const firebaseConfig = {
  apiKey: "AIzaSyCnvOLOgM8A7888OnyZQUtgjPadc-aUGTc",
  authDomain: "appsos-3bdab.firebaseapp.com",
  projectId: "appsos-3bdab",
  storageBucket: "appsos-3bdab.appspot.com",
  messagingSenderId: "781085116494",
  appId: "1:781085116494:web:fe579a52fac5615a361b1f",
  measurementId: "G-0DKFDY6YQP"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);

// Initialize Firestore
export const db = getFirestore(app);

// Optional: Initialize Analytics
export const analytics = getAnalytics(app);

export function createApp() {
  const app = createSSRApp(App);
  return { app };
}
