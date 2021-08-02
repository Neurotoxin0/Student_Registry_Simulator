# Student Registry Simulator
A simple version of student registry program, features includes but not limited to: courses enroll & drop, course auto & manual schedule, grade override, external student list, etc.

----
# Available commands:
- REG: register a new student in registry
    * with student's name and student#
    * e.g. `reg JohnDoe 13579`

- DEL: delete a student from registry
    * with student's name and student#
    * e.g. `del JohnDoe 13579`

- ADDC: add a student to an active course
    * with student's name and student#
    * e.g. `addc 13579 CPS209`

- DROPC: drop student from course
    * with student's name and student#
    * e.g. `dropc 13579 CPS209`

- PAC: print all active courses
    * No CLA needed

- PCL: print class list for given course
    * with course#
    * e.g. `pcl CPS209`

- PGR: print name, id and grade of all students in active course
    * with course#
    * e.g. `pgr CPS209`

- PSC: print all credit courses of given student
    * with student#
    * e.g. `psc 13579`

- PST: print student transcript
    * with student#
    * e.g. `pst 13579`

- SFG: set final grade of student
    * with course#,student# and grade in numeric
    * e.g. `sfg CPS209 13579 100`

- SCN: sort list if students in course by name
    * with course#
    * e.g. `scn CPS209`

- SCH: scheule lecture time for a course
    * with course#, Day, StartTime and Duration(h)
    * e.g. `sch CPS209 Mon 0800 2`

- SCHA: auto schedule for a course
    * with course# and duration
    * e.g. `scha CPS209 2`

- CSCH: clear the schedule for given course
    * with course#
    * e.g. `csch CPS209`

- PSCH: print the entire schedule
    * No CLA needed
