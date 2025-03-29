import React from "react";
import { inventoryActor } from "../utils/icpClient.js";
import { usePolling } from "../hooks/usePolling";

const Inventory = () => {
  const fetchMedicines = async () => {
    const medicines = await inventoryActor.getAllMedicines();
    return medicines.map(([id, med]) => ({
      id,
      batchno: med.batchno,
      name: med.name,
      quantity: med.quantity,
      expiry: new Date(Number(med.expiry) / 1000000).toISOString(),
    }));
  };

  const { data: inventoryData, error, isLoading, lastUpdate } = usePolling(fetchMedicines);

  const formatDate = (dateString) => {
    try {
      const date = new Date(Number(dateString) / 1_000_000);
      return date.toISOString().split('T')[0]; // Returns YYYY-MM-DD
    } catch (error) {
      return 'Invalid Date';
    }
  };

  const formatLastUpdate = (date) => {
    if (!date) return '';
    return new Date(date).toLocaleTimeString();
  };

  if (isLoading) return <div className="p-6">Loading...</div>;
  if (error) return <div className="p-6 text-red-500">Error: {error.message}</div>;

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-3xl font-bold text-gray-700" style={{ fontFamily: "Poppins, sans-serif" }}>
          Inventory
        </h1>
        <div className="text-sm text-gray-500">
          Last updated: {formatLastUpdate(lastUpdate)}
        </div>
      </div>

      <div className="overflow-x-auto">
        <table className="w-full border-collapse shadow-lg rounded-lg overflow-hidden bg-white">
          <thead>
            <tr className="bg-blue-700 text-white">
              <th className="px-4 py-3">#</th>
              <th className="px-4 py-3">Medicine ID</th>
              <th className="px-4 py-3">Batch No</th>
              <th className="px-4 py-3">Medicine Name</th>
              <th className="px-4 py-3">Stock</th>
              <th className="px-4 py-3">Expiry Date</th>
            </tr>
          </thead>
          <tbody>
            {inventoryData.map((item, index) => (
              <tr key={item.id} className={`${index % 2 === 0 ? "bg-blue-200" : "bg-white"} text-center`}>
                <td className="px-4 py-3">{index + 1}</td>
                <td className="px-4 py-3 text-sm font-mono">{item.id}</td>
                <td className="px-4 py-3">{item.batchno}</td>
                <td className="px-4 py-3">{item.name}</td>
                <td className={`px-4 py-3 font-semibold ${item.quantity < 10 ? "text-red-600" : "text-green-600"}`}>
                  {item.quantity}
                </td>
                <td className="px-4 py-3">{formatDate(item.expiry)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div className={`fixed bottom-4 right-4 p-2 rounded-full ${error ? "bg-red-500" : "bg-green-500"}`}>
        <span className="text-white text-sm px-3">
          {error ? "Disconnected" : "Connected"}
        </span>
      </div>
    </div>
  );
};

export default Inventory;
