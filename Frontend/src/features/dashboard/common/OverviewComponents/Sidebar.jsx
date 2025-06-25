import {
  Search,
  Plus,
  ChevronDown,
  ChevronRight,
  ExternalLink,
} from "lucide-react";
const Sidebar = () => {
  return (
      <>
      <div className="bg-slate-800 text-white rounded-xl overflow-hidden p-6 shadow-md">
            <h3 className="font-semibold mb-6">SEAMEN AVAILABILITY STATUS</h3>
            <div className="flex justify-center mb-6">
              <div className="relative w-32 h-32">
               <svg
                    className="w-32 h-32 transform -rotate-90 absolute"
                    viewBox="0 0 36 36"
                    >
                    {/* Background track */}
                    <circle
                        cx="18"
                        cy="18"
                        r="15.9155"
                        fill="none"
                        stroke="#374151"
                        strokeWidth="2"
                    />

                    {/* Red (15%) - Drawn first */}
                    <circle
                        cx="18"
                        cy="18"
                        r="15.9155"
                        fill="none"
                        stroke="#EF4444"
                        strokeWidth="2"
                        strokeDasharray="15, 100"
                        strokeDashoffset="0"
                        strokeLinecap="butt"
                    />

                    {/* Green (85%) - Drawn after red */}
                    <circle
                        cx="18"
                        cy="18"
                        r="15.9155"
                        fill="none"
                        stroke="#10B981"
                        strokeWidth="2"
                        strokeDasharray="85, 100"
                        strokeDashoffset="-15"
                        strokeLinecap="butt"
                    />
                    </svg>

                <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 flex justify-center items-center">
                  <span className="text-lg font-bold">85%</span>
                </div>
              </div>
            </div>
            <div className="space-y-2">
              <div className="flex items-center gap-2">
                <div className="w-3 h-3 bg-green-500 rounded-sm"></div>
                <span className="text-sm">On Duty</span>
              </div>
              <div className="flex items-center gap-2">
                <div className="w-3 h-3 bg-red-500 rounded-sm"></div>
                <span className="text-sm">On Leave</span>
              </div>
            </div>
          </div>

          <div className="bg-slate-800 text-white rounded-xl p-6 shadow-md">
            <div className="flex justify-between items-center mb-6">
              <h3 className="font-semibold">SEAMEN CONTRACT</h3>
              <div className="flex items-center space-x-1 text-blue-400 text-sm cursor-pointer">
                <span>Go to Check Details</span>
                <ExternalLink className="w-4 h-4" />
              </div>
            </div>
            <div className="space-y-4">
              {[
                "WORKING DAYS",
                "SICK DAYS",
                "WORKING DAYS",
                "OVERTIME HOURS",
                "SICK DAYS",
              ].map((label, i) => (
                <div key={i} className="flex justify-between items-center">
                  <span className="text-sm text-gray-300">{label}</span>
                  <div className="flex items-center gap-2">
                    <div className="w-16 h-1 bg-gray-600 rounded overflow-hidden">
                      <div className="w-8 h-1 bg-white rounded"></div>
                    </div>
                    <span className="text-sm">$</span>
                  </div>
                </div>
              ))}
            </div>
            <div className="pt-4 mt-6 border-t border-gray-600 text-xs text-gray-400 flex justify-between">
              <span>1 Month</span>
              <span>6 Months</span>
              <span>9 Months</span>
              <span>1 Year</span>
              <span>1 Year 6 Months</span>
            </div>
          </div></>
  );
};
export default Sidebar;
