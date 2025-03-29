import { useState, useEffect, useCallback } from 'react';

const INITIAL_INTERVAL = 2000; // 2 seconds
const MAX_INTERVAL = 10000; // 10 seconds
const BACKOFF_RATE = 1.5;

export const usePolling = (fetchFn, options = {}) => {
  const [data, setData] = useState(null);
  const [error, setError] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [lastUpdate, setLastUpdate] = useState(null);
  const [currentInterval, setCurrentInterval] = useState(INITIAL_INTERVAL);

  const pollData = useCallback(async () => {
    try {
      const result = await fetchFn();
      setData(result);
      setError(null);
      setLastUpdate(new Date());
      setCurrentInterval(INITIAL_INTERVAL); // Reset interval on success
    } catch (err) {
      setError(err);
      // Implement exponential backoff
      setCurrentInterval(prev => Math.min(prev * BACKOFF_RATE, MAX_INTERVAL));
    } finally {
      setIsLoading(false);
    }
  }, [fetchFn]);

  useEffect(() => {
    pollData();
    const interval = setInterval(pollData, currentInterval);
    return () => clearInterval(interval);
  }, [currentInterval, pollData]);

  return { data, error, isLoading, lastUpdate };
};
