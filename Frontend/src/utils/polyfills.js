if (typeof window !== 'undefined') {
  window.global = window;
}

export const polyFillProcess = {
  env: {
    NODE_ENV: 'development'
  }
};

if (typeof process === 'undefined') {
  window.process = polyFillProcess;
}
