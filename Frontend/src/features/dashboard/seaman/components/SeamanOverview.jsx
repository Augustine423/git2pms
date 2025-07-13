import { useState } from "react";
import { Link } from "react-router-dom";
import { Ellipsis } from "lucide-react";

const data = [
  {
    id: 1,
    name: "John Smith",
    sailorCode: "SC001",
    nationality: "us",
    position: "Captain",
    employmentDate: "2023-01-15",
  },
  {
    id: 2,
    name: "Maria Garcia",
    sailorCode: "SC002",
    nationality: "🇪🇸",
    position: "Engineer",
    employmentDate: "2023-02-20",
  },
  {
    id: 3,
    name: "Ahmed Hassan",
    sailorCode: "SC003",
    nationality: "🇪🇬",
    position: "Navigator",
    employmentDate: "2023-03-10",
  },
  {
    id: 4,
    name: "Liu Wei",
    sailorCode: "SC004",
    nationality: "🇨🇳",
    position: "Cook",
    employmentDate: "2023-04-05",
  },
  {
    id: 5,
    name: "Hans Mueller",
    sailorCode: "SC005",
    nationality: "🇩🇪",
    position: "Mechanic",
    employmentDate: "2023-05-12",
  },
  {
    id: 6,
    name: "Pierre Dubois",
    sailorCode: "SC006",
    nationality: "🇫🇷",
    position: "Officer",
    employmentDate: "2023-06-18",
  },
  {
    id: 7,
    name: "Yuki Tanaka",
    sailorCode: "SC007",
    nationality: "🇯🇵",
    position: "Radio Op",
    employmentDate: "2023-07-22",
  },
  {
    id: 8,
    name: "Carlos Silva",
    sailorCode: "SC008",
    nationality: "🇧🇷",
    position: "Deckhand",
    employmentDate: "2023-08-30",
  },
  {
    id: 9,
    name: "Anna Kowalski",
    sailorCode: "SC009",
    nationality: "🇵🇱",
    position: "Medic",
    employmentDate: "2023-09-14",
  },
  {
    id: 10,
    name: "Raj Patel",
    sailorCode: "SC010",
    nationality: "🇮🇳",
    position: "Engineer",
    employmentDate: "2023-10-08",
  },
  {
    id: 11,
    name: "Yuki Tanaka",
    sailorCode: "SC007",
    nationality: "🇯🇵",
    position: "Radio Op",
    employmentDate: "2023-07-22",
  },
  {
    id: 12,
    name: "Carlos Silva",
    sailorCode: "SC008",
    nationality: "🇧🇷",
    position: "Deckhand",
    employmentDate: "2023-08-30",
  },
  {
    id: 13,
    name: "Anna Kowalski",
    sailorCode: "SC009",
    nationality: "🇵🇱",
    position: "Medic",
    employmentDate: "2023-09-14",
  },
  {
    id: 14,
    name: "Raj Patel",
    sailorCode: "SC010",
    nationality: "🇮🇳",
    position: "Engineer",
    employmentDate: "2023-10-08",
  },
];

const SeamanOverview = () => {
  const [currentPage, setCurrentPage] = useState(1);
  const rowsPerPage = 8;
  const totalPages = Math.ceil(data.length / rowsPerPage);
  const [seamen, setSeamen] = useState(data);

  const currentData = seamen.slice(
    (currentPage - 1) * rowsPerPage,
    currentPage * rowsPerPage
  );

  return (
    <div className="min-h-screen w-full mt-4 shadow-2xl overflow-hidden">
      <div className="bg-white mx-4 rounded-[30px] p-6">
        <div className="overflow-x-auto mt-2">
          <table className="w-full border-separate border-spacing-y-1">
            <thead>
              <tr className="bg-white rounded-lg shadow-sm text-sm text-black">
                <th className="text-center p-4 rounded-l-2xl">Profile</th>
                <th className="text-center p-4">Name</th>
                <th className="text-center p-4">Sailor Code</th>
                <th className="text-center p-4">Nationality</th>
                <th className="text-center p-4">Position</th>
                <th className="text-center p-4">Employment Date</th>
                <th className="text-center p-4 rounded-r-2xl"></th>
              </tr>
            </thead>
            <tbody>
              {currentData.map((row) => (
                <tr
                  key={row.id}
                  className="bg-gray-300 border-b border-b-black hover:bg-gray-200"
                >
                  <td className="py-4 text-center">
                    <div className="flex justify-center">
                      <div className="bg-white rounded-xl px-8 py-3 min-w-[120px]">
                        <span className="text-sm text-black">{row.id}</span>
                      </div>
                    </div>
                  </td>
                  <td className="px-4 py-4 text-center text-sm text-black">
                    {row.name}
                  </td>
                  <td className="px-4 py-4 text-center text-sm text-black">
                    {row.sailorCode}
                  </td>
                  <td className="px-4 py-4 text-center text-2xl">
                    {row.nationality}
                  </td>
                  <td className="px-4 py-4 text-center text-sm text-black">
                    {row.position}
                  </td>
                  <td className="px-4 py-4 text-center text-sm text-black">
                    <div className="flex items-center justify-center space-x-2">
                      <span>{row.employmentDate}</span>
                    </div>
                  </td>
                  <td className="px-4 py-4 text-center">
                    <Link
                      to={`/`}
                      className="text-black hover:text-gray-600"
                    >
                      <Ellipsis className="w-6 h-6" />
                    </Link>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        {/* Pagination */}
        <div className="flex justify-center mt-2 space-x-2">
          {Array.from({ length: totalPages }, (_, i) => i + 1).map((page) => (
            <button
              key={page}
              onClick={() => setCurrentPage(page)}
              className={`w-8 h-8 rounded-full text-sm font-medium ${
                currentPage === page
                  ? "bg-gray-800 text-white"
                  : "bg-gray-600 text-white hover:bg-gray-700"
              }`}
            >
              {page}
            </button>
          ))}
        </div>
      </div>
    </div>
  );
};

export default SeamanOverview;