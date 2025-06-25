import {
  Search,
  Plus,
  ChevronDown,
  ChevronRight,
  ExternalLink,
} from "lucide-react";
const seamenData = [
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
  {
    name: "Seaman Name",
    position: "Position",
    assignedShip: "SHIP'S SUN",
    status: "Active",
    nationality: "🇺🇸",
    sailorCode: "F20015",
  },
];
const workingCrewImages = [
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
];

const nonWorkingCrewImages = [
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
  "/placeholder.svg?height=32&width=32",
];
const shipData = [
  { name: "WANDERLUST", description: "Ship Description" },
  { name: "EASTERN WINDS", description: "Ship Description" },
  { name: "SKIPPER", description: "Ship Description" },
  { name: "OCEAN BLUE", description: "Ship Description" },
];

const SeamanInfo = () => {
    return(
        <>
         <div className="lg:col-span-2 space-y-6">
          {/* Seaman Information Table */}
          <div className="bg-white rounded-xl p-4 shadow-md">
            <div className="flex items-center justify-between mb-4">
              <h2 className="text-gray-900 font-semibold text-lg">
                SEAMAN INFORMATION
              </h2>
            </div>

            <div className="overflow-auto">
              <table className="w-full text-sm">
                <thead className="bg-gray-100 text-gray-600 uppercase">
                  <tr>
                    <th className="px-4 py-3 text-left">Name</th>
                    <th className="px-4 py-3 text-left">Position</th>
                    <th className="px-4 py-3 text-left">Assigned Ship</th>
                    <th className="px-4 py-3 text-left">Status</th>
                    <th className="px-4 py-3 text-left">Nationality</th>
                    <th className="px-4 py-3 text-left">Sailor Code</th>
                  </tr>
                </thead>
                <tbody className="divide-y divide-gray-200">
                  {seamenData.map((seaman, i) => (
                    <tr key={i} className="hover:bg-gray-50">
                      <td className="px-4 py-3">{seaman.name}</td>
                      <td className="px-4 py-3">{seaman.position}</td>
                      <td className="px-4 py-3">{seaman.assignedShip}</td>
                      <td className="px-4 py-3">
                        <span className="flex items-center gap-2 px-2 py-1 bg-green-100 text-green-700 text-xs rounded-full">
                          <div className="w-2 h-2 bg-green-500 rounded-full"></div>
                          {seaman.status}
                        </span>
                      </td>
                      <td className="px-4 py-3 text-lg">
                        {seaman.nationality}
                      </td>
                      <td className="px-4 py-3">{seaman.sailorCode}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>

          {/* Ship and Working Status Cards */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="bg-white rounded-xl p-4 shadow-md">
              <div className="flex items-center justify-between mb-4">
                <h3 className="font-semibold text-gray-900">SHIP</h3>
                <Plus className="w-5 h-5 text-gray-400" />
              </div>
              <div className="space-y-3">
                {shipData.map((ship, i) => (
                  <div
                    key={i}
                    className="flex justify-between items-center bg-gray-50 p-3 rounded-lg"
                  >
                    <div className="flex items-center gap-3">
                      <div className="w-8 h-8 bg-gray-300 rounded flex items-center justify-center">
                        <div className="w-4 h-4 bg-gray-600 rounded"></div>
                      </div>
                      <div>
                        <div className="font-medium text-sm">{ship.name}</div>
                        <div className="text-xs text-gray-500">
                          {ship.description}
                        </div>
                      </div>
                    </div>
                    <ChevronRight className="w-4 h-4 text-gray-400" />
                  </div>
                ))}
              </div>
              <div className="mt-4 flex justify-center">
                <ChevronDown className="w-5 h-5 text-gray-400" />
              </div>
            </div>

            <div className="bg-white rounded-xl p-4 shadow-md">
              <div className="flex items-center justify-between mb-4">
                <h3 className="font-semibold text-gray-900">
                  WORKING / NON-WORKING
                </h3>
                <Plus className="w-5 h-5 text-gray-400" />
              </div>
              <div className="space-y-4">
                {[
                  { title: "Working", data: workingCrewImages },
                  { title: "Non-Working", data: nonWorkingCrewImages },
                ].map((group, idx) => (
                  <div key={idx}>
                    <div className="flex justify-between items-center mb-2">
                      <span className="text-sm font-medium">{group.title}</span>
                      <ChevronRight className="w-4 h-4 text-gray-400" />
                    </div>
                    <div className="flex -space-x-2">
                      {group.data.map((_, i) => (
                        <div
                          key={i}
                          className="w-8 h-8 bg-gray-300 rounded-full border-2 border-white"
                        ></div>
                      ))}
                    </div>
                  </div>
                ))}
              </div>
              <div className="mt-4 flex justify-between items-center">
                <div className="flex gap-4">
                  <div className="flex items-center gap-1">
                    <div className="w-2 h-2 bg-green-500 rounded-full"></div>
                    <span className="text-xs text-gray-600">Active</span>
                  </div>
                  <div className="flex items-center gap-1">
                    <div className="w-2 h-2 bg-red-500 rounded-full"></div>
                    <span className="text-xs text-gray-600">Inactive</span>
                  </div>
                </div>
                <div className="flex gap-2">
                  <button className="px-3 py-1 bg-gray-100 text-xs rounded">
                    All
                  </button>
                  <button className="px-3 py-1 bg-blue-500 text-white text-xs rounded">
                    Filter
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
        </>
    );
};
export default SeamanInfo;