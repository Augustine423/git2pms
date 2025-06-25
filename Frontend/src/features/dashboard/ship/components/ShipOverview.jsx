import React, { useState } from "react";
import { FaCircle } from "react-icons/fa6";
import Pagination from "../../../../common/Pagination";
import { Ellipsis } from "lucide-react";

const data = [
  {
    ship: "Ship A",
    callSign: "A123",
    operator: "Operator X",
    status: "green",
    kind: "Cargo",
    imo: "IMO123",
  },
  {
    ship: "Ship B",
    callSign: "B456",
    operator: "Operator Y",
    status: "red",
    kind: "Tanker",
    imo: "IMO456",
  },
  {
    ship: "Ship P",
    callSign: "A123",
    operator: "Operator X",
    status: "green",
    kind: "Cargo",
    imo: "IMO123",
  },
  {
    ship: "Ship O",
    callSign: "B456",
    operator: "Operator Y",
    status: "red",
    kind: "Tanker",
    imo: "IMO456",
  },
  {
    ship: "Ship C",
    callSign: "C789",
    operator: "Operator Z",
    status: "green",
    kind: "Cruise",
    imo: "IMO789",
  },
  {
    ship: "Ship D",
    callSign: "D012",
    operator: "Operator Q",
    status: "green",
    kind: "Cargo",
    imo: "IMO012",
  },
  {
    ship: "Ship E",
    callSign: "E345",
    operator: "Operator W",
    status: "red",
    kind: "Tanker",
    imo: "IMO345",
  },
  {
    ship: "Ship F",
    callSign: "F678",
    operator: "Operator V",
    status: "green",
    kind: "Cruise",
    imo: "IMO678",
  },
  {
    ship: "Ship T",
    callSign: "A123",
    operator: "Operator X",
    status: "green",
    kind: "Cargo",
    imo: "IMO123",
  },
  {
    ship: "Ship R",
    callSign: "B456",
    operator: "Operator Y",
    status: "red",
    kind: "Tanker",
    imo: "IMO456",
  },
  {
    ship: "Ship G",
    callSign: "C789",
    operator: "Operator Z",
    status: "green",
    kind: "Cruise",
    imo: "IMO789",
  },
  {
    ship: "Ship H",
    callSign: "D012",
    operator: "Operator Q",
    status: "green",
    kind: "Cargo",
    imo: "IMO012",
  },
  {
    ship: "Ship I",
    callSign: "E345",
    operator: "Operator W",
    status: "red",
    kind: "Tanker",
    imo: "IMO345",
  },
  {
    ship: "Ship J",
    callSign: "F678",
    operator: "Operator V",
    status: "green",
    kind: "Cruise",
    imo: "IMO678",
  },
  // Add more rows...
];

const ShipOverview = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const rowsPerPage = 8;
  const totalPages = Math.ceil(data.length / rowsPerPage);

  const currentData = data.slice(
    (currentPage - 1) * rowsPerPage,
    currentPage * rowsPerPage
  );

  return (
    <>
      <div className="min-h-screen bg-gray-300 p-2">
        <div className="max-w-9xl mx-auto">
          
          <div className="overflow-x-auto mt-2">
            <table className="w-full border-separate border-spacing-y-1">
              <thead>
                <tr className="bg-white rounded-lg shadow-sm text-sm text-black">
                  <th className="text-center p- rounded-l-2xl">Ship</th>
                  <th className="text-center p-4">Call Sign</th>
                  <th className="text-center p-4">Operator</th>
                  <th className="text-center p-4">Status</th>
                  <th className="text-center p-4">Kind Of Ship</th>
                  <th className="text-center p-4">IMO Number</th>
                  <th className="text-center p-4 rounded-r-2xl"></th>
                </tr>
              </thead>
              <tbody>
                {currentData.map((row, index) => (
                  <tr
                    key={index}
                    className="bg-gray-300 border-b border-black hover:bg-gray-200"
                  >
                    <td className="py-4 text-center">
                      <div className="flex justify-center">
                        <div className="bg-white rounded-xl px-8 py-3 min-w-[120px]">
                          <span className="text-sm text-black">{row.ship}</span>
                        </div>
                      </div>
                    </td>
                    <td className="px-4 py-4 text-center text-sm text-black">
                      {row.callSign}
                    </td>
                    <td className="px-4 py-4 text-center text-sm text-black">
                      {row.operator}
                    </td>
                    <td className="px-4 py-4 text-center">
                      <div className="flex justify-center">
                        <FaCircle
                          className={`w-3 h-3 ${
                            row.status === "green"
                              ? "text-green-500 fill-green-500"
                              : "text-red-500 fill-red-500"
                          }`}
                        />
                      </div>
                    </td>
                    <td className="px-4 py-4 text-center text-sm text-black">
                      {row.kind}
                    </td>
                    <td className="px-4 py-4 text-center text-sm text-black">
                      {row.imo}
                    </td>
                    <td>
                      <Ellipsis />
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {/* Pagination */}
          <div className="flex justify-center mt-2 space-x-2">
            <Pagination
              currentPage={currentPage}
              totalPages={totalPages}
              onPageChange={setCurrentPage}
            />
          </div>
        </div>
      </div>
    </>
  );
};

export default ShipOverview;
